package com.zeddic.service.graph.depression.impl;

import com.zeddic.entity.graph.depression.Risk;
import com.zeddic.repository.graph.RiskRepository;
import com.zeddic.service.graph.depression.RiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Zeddic
 */
@Service
public class RiskServiceImpl implements RiskService {

    private final RiskRepository riskRepository;

    @Autowired
    public RiskServiceImpl(RiskRepository riskRepository) {
        this.riskRepository = riskRepository;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Risk saveRisk(Risk risk) {
        // 设置创建时间和更新时间
        LocalDateTime now = LocalDateTime.now();
        risk.setCreateTime(now);
        risk.setUpdateTime(now);

        // 初始化集合类字段，避免空指针异常
        if (risk.getPreventiveMeasures() == null) {
            risk.setPreventiveMeasures(new HashSet<>());
        }

        return riskRepository.save(risk);
    }

    @Override
    public Optional<Risk> findRiskById(Long id) {
        return riskRepository.findById(id);
    }

    @Override
    public Optional<Risk> findRiskByName(String name) {
        return riskRepository.findByName(name);
    }

    @Override
    public List<Risk> findAllRisks() {
        return riskRepository.findAll();
    }

    @Override
    public List<Risk> findRisksByCategory(String category) {
        return riskRepository.findByCategory(category);
    }

    @Override
    public List<Risk> findRisksByEvidenceLevel(String evidenceLevel) {
        return riskRepository.findByEvidenceLevel(evidenceLevel);
    }

    @Override
    public List<Risk> findRisksByImpactFactorBetween(Double minFactor, Double maxFactor) {
        return riskRepository.findByImpactFactorBetween(minFactor, maxFactor);
    }

    @Override
    public List<Risk> findRisksByPreventiveMeasure(String measure) {
        return riskRepository.findByPreventiveMeasure(measure);
    }

    @Override
    public List<Risk> findRisksByRelatedDiseaseName(String diseaseName) {
        return riskRepository.findByRelatedDiseaseName(diseaseName);
    }

    @Override
    public List<Risk> findAllRisksOrderByImpactFactorDesc() {
        return riskRepository.findAllOrderByImpactFactorDesc();
    }

    @Override
    public List<Risk> findRisksByDescriptionContaining(String keyword) {
        return riskRepository.findByDescriptionContaining(keyword);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Risk updateRisk(Risk risk) {
        Optional<Risk> existingRiskOpt = riskRepository.findById(risk.getId());

        if (existingRiskOpt.isPresent()) {
            Risk existingRisk = existingRiskOpt.get();

            // 保留原有的创建时间
            risk.setCreateTime(existingRisk.getCreateTime());
            // 更新时间设为当前时间
            risk.setUpdateTime(LocalDateTime.now());

            // 保留集合类数据，避免丢失
            if (risk.getPreventiveMeasures() == null && existingRisk.getPreventiveMeasures() != null) {
                risk.setPreventiveMeasures(existingRisk.getPreventiveMeasures());
            }

            return riskRepository.save(risk);
        } else {
            throw new RuntimeException("风险因素不存在，ID: " + risk.getId());
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Risk addPreventiveMeasuresToRisk(Long riskId, Set<String> preventiveMeasures) {
        Risk risk = riskRepository.findById(riskId)
                .orElseThrow(() -> new RuntimeException("风险因素不存在，ID: " + riskId));

        if (risk.getPreventiveMeasures() == null) {
            risk.setPreventiveMeasures(new HashSet<>());
        }
        risk.getPreventiveMeasures().addAll(preventiveMeasures);
        risk.setUpdateTime(LocalDateTime.now());

        return riskRepository.save(risk);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteRisk(Long id) {
        if (riskRepository.existsById(id)) {
            riskRepository.deleteById(id);
        } else {
            throw new RuntimeException("风险因素不存在，ID: " + id);
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Risk removePreventiveMeasureFromRisk(Long riskId, String measure) {
        Risk risk = riskRepository.findById(riskId)
                .orElseThrow(() -> new RuntimeException("风险因素不存在，ID: " + riskId));

        if (risk.getPreventiveMeasures() != null) {
            risk.getPreventiveMeasures().remove(measure);
            risk.setUpdateTime(LocalDateTime.now());
            return riskRepository.save(risk);
        }

        return risk;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public List<Risk> saveAllRisks(List<Risk> risks) {
        LocalDateTime now = LocalDateTime.now();

        // 设置创建时间和更新时间，初始化集合字段
        risks.forEach(risk -> {
            risk.setCreateTime(now);
            risk.setUpdateTime(now);
            if (risk.getPreventiveMeasures() == null) {
                risk.setPreventiveMeasures(new HashSet<>());
            }
        });

        return riskRepository.saveAll(risks);
    }

    @Override
    public Map<String, Long> countRisksByCategory() {
        List<Risk> allRisks = riskRepository.findAll();
        Map<String, Long> categoryCounts = new HashMap<>(allRisks.size());

        // 按类别统计风险因素数量
        for (Risk risk : allRisks) {
            String category = risk.getCategory();
            if (category != null) {
                categoryCounts.put(category, categoryCounts.getOrDefault(category, 0L) + 1);
            }
        }

        return categoryCounts;
    }

    @Override
    public List<Risk> findTopNthRisksByImpactFactor(int n) {
        List<Risk> sortedRisks = riskRepository.findAllOrderByImpactFactorDesc();
        return sortedRisks.stream().limit(n).collect(Collectors.toList());
    }
}
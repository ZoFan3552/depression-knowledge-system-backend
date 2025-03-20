package com.zeddic.service.graph.depression.impl;

import com.zeddic.entity.graph.depression.Symptom;
import com.zeddic.repository.graph.SymptomRepository;
import com.zeddic.service.graph.depression.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SymptomServiceImpl implements SymptomService {

    private final SymptomRepository symptomRepository;

    @Autowired
    public SymptomServiceImpl(SymptomRepository symptomRepository) {
        this.symptomRepository = symptomRepository;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Symptom saveSymptom(Symptom symptom) {
        // 设置创建时间和更新时间
        LocalDateTime now = LocalDateTime.now();
        symptom.setCreateTime(now);
        symptom.setUpdateTime(now);

        // 初始化集合类字段，避免空指针异常
        if (symptom.getManifestations() == null) {
            symptom.setManifestations(new HashSet<>());
        }

        return symptomRepository.save(symptom);
    }

    @Override
    public Optional<Symptom> findSymptomById(Long id) {
        return symptomRepository.findById(id);
    }

    @Override
    public Optional<Symptom> findSymptomByName(String name) {
        return symptomRepository.findByName(name);
    }

    @Override
    public List<Symptom> findAllSymptoms() {
        return symptomRepository.findAll();
    }

    @Override
    public List<Symptom> findSymptomsByCategory(String category) {
        return symptomRepository.findByCategory(category);
    }

    @Override
    public List<Symptom> findSymptomsBySeverityBetween(Integer minSeverity, Integer maxSeverity) {
        return symptomRepository.findBySeverityBetween(minSeverity, maxSeverity);
    }

    @Override
    public List<Symptom> findCommonSymptoms() {
        return symptomRepository.findByIsCommonTrue();
    }

    @Override
    public List<Symptom> findUncommonSymptoms() {
        return symptomRepository.findByIsCommonFalse();
    }

    @Override
    public List<Symptom> findSymptomsByManifestation(String manifestation) {
        return symptomRepository.findByManifestation(manifestation);
    }

    @Override
    public List<Symptom> findSymptomsByRelatedDiseaseName(String diseaseName) {
        return symptomRepository.findByRelatedDiseaseName(diseaseName);
    }

    @Override
    public List<Symptom> findAllSymptomsOrderBySeverityDesc() {
        return symptomRepository.findAllOrderBySeverityDesc();
    }

    @Override
    public List<Symptom> findSymptomsByDescriptionContaining(String keyword) {
        return symptomRepository.findByDescriptionContaining(keyword);
    }

    @Override
    public List<Symptom> findSymptomsByDuration(String duration) {
        return symptomRepository.findByDuration(duration);
    }

    @Override
    public List<Symptom> findSymptomsByCategoryAndIsCommon(String category, Boolean isCommon) {
        return symptomRepository.findByCategoryAndIsCommon(category, isCommon);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Symptom updateSymptom(Symptom symptom) {
        Optional<Symptom> existingSymptomOpt = symptomRepository.findById(symptom.getId());

        if (existingSymptomOpt.isPresent()) {
            Symptom existingSymptom = existingSymptomOpt.get();

            // 保留原有的创建时间
            symptom.setCreateTime(existingSymptom.getCreateTime());
            // 更新时间设为当前时间
            symptom.setUpdateTime(LocalDateTime.now());

            // 保留集合类数据，避免丢失
            if (symptom.getManifestations() == null && existingSymptom.getManifestations() != null) {
                symptom.setManifestations(existingSymptom.getManifestations());
            }

            return symptomRepository.save(symptom);
        } else {
            throw new RuntimeException("症状不存在，ID: " + symptom.getId());
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Symptom addManifestationsToSymptom(Long symptomId, Set<String> manifestations) {
        Symptom symptom = symptomRepository.findById(symptomId)
                .orElseThrow(() -> new RuntimeException("症状不存在，ID: " + symptomId));

        if (symptom.getManifestations() == null) {
            symptom.setManifestations(new HashSet<>());
        }
        symptom.getManifestations().addAll(manifestations);
        symptom.setUpdateTime(LocalDateTime.now());

        return symptomRepository.save(symptom);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteSymptom(Long id) {
        if (symptomRepository.existsById(id)) {
            symptomRepository.deleteById(id);
        } else {
            throw new RuntimeException("症状不存在，ID: " + id);
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Symptom removeManifestationFromSymptom(Long symptomId, String manifestation) {
        Symptom symptom = symptomRepository.findById(symptomId)
                .orElseThrow(() -> new RuntimeException("症状不存在，ID: " + symptomId));

        if (symptom.getManifestations() != null) {
            symptom.getManifestations().remove(manifestation);
            symptom.setUpdateTime(LocalDateTime.now());
            return symptomRepository.save(symptom);
        }

        return symptom;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public List<Symptom> saveAllSymptoms(List<Symptom> symptoms) {
        LocalDateTime now = LocalDateTime.now();

        // 设置创建时间和更新时间，初始化集合字段
        symptoms.forEach(symptom -> {
            symptom.setCreateTime(now);
            symptom.setUpdateTime(now);
            if (symptom.getManifestations() == null) {
                symptom.setManifestations(new HashSet<>());
            }
        });

        return symptomRepository.saveAll(symptoms);
    }

    @Override
    public Map<String, Long> countSymptomsByCategory() {
        List<Symptom> allSymptoms = symptomRepository.findAll();
        Map<String, Long> categoryCounts = new HashMap<>(allSymptoms.size());

        // 按分类统计症状数量
        for (Symptom symptom : allSymptoms) {
            String category = symptom.getCategory();
            if (category != null) {
                categoryCounts.put(category, categoryCounts.getOrDefault(category, 0L) + 1);
            }
        }

        return categoryCounts;
    }

    @Override
    public List<Symptom> findTopNthSymptomsBySeverity(int n) {
        List<Symptom> sortedSymptoms = symptomRepository.findAllOrderBySeverityDesc();
        return sortedSymptoms.stream().limit(n).collect(Collectors.toList());
    }

    @Override
    public Optional<Symptom> findMostSevereSymptomByCategory(String category) {
        List<Symptom> categorySymptoms = symptomRepository.findByCategory(category);
        return categorySymptoms.stream()
                .max(Comparator.comparing(Symptom::getSeverity));
    }

    @Override
    public boolean existsSymptomById(Long id) {
        return symptomRepository.existsById(id);
    }
}
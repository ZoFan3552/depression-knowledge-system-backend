package com.zeddic.service.graph.depression.impl;

import com.zeddic.entity.graph.depression.Therapy;
import com.zeddic.repository.graph.TherapyRepository;
import com.zeddic.service.graph.depression.TherapyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Zeddic
 */
@Service
public class TherapyServiceImpl implements TherapyService {

    private final TherapyRepository therapyRepository;

    @Autowired
    public TherapyServiceImpl(TherapyRepository therapyRepository) {
        this.therapyRepository = therapyRepository;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Therapy saveTherapy(Therapy therapy) {
        // 设置创建时间和更新时间
        LocalDateTime now = LocalDateTime.now();
        therapy.setCreateTime(now);
        therapy.setUpdateTime(now);

        return therapyRepository.save(therapy);
    }

    @Override
    public Optional<Therapy> findTherapyById(Long id) {
        return therapyRepository.findById(id);
    }

    @Override
    public Optional<Therapy> findTherapyByName(String name) {
        return therapyRepository.findByName(name);
    }

    @Override
    public List<Therapy> findAllTherapies() {
        return therapyRepository.findAll();
    }

    @Override
    public List<Therapy> findTherapiesByCategory(String category) {
        return therapyRepository.findByCategory(category);
    }

    @Override
    public List<Therapy> findTherapiesByApproach(String approach) {
        return therapyRepository.findByApproach(approach);
    }

    @Override
    public List<Therapy> findTherapiesByDurationCourse(String durationCourse) {
        return therapyRepository.findByDurationCourse(durationCourse);
    }

    @Override
    public List<Therapy> findTherapiesBySideEffectsContaining(String keyword) {
        return therapyRepository.findBySideEffectsContaining(keyword);
    }

    @Override
    public List<Therapy> findTherapiesByRelatedDiseaseName(String diseaseName) {
        return therapyRepository.findByRelatedDiseaseName(diseaseName);
    }

    @Override
    public List<Therapy> findTherapiesByCategoryAndApproach(String category, String approach) {
        return therapyRepository.findByCategoryAndApproach(category, approach);
    }

    @Override
    public List<Therapy> findTherapiesByDescriptionContaining(String keyword) {
        return therapyRepository.findByDescriptionContaining(keyword);
    }

    @Override
    public List<Therapy> findTherapiesByDiseaseId(Long diseaseId) {
        return therapyRepository.findByDiseaseId(diseaseId);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Therapy updateTherapy(Therapy therapy) {
        Optional<Therapy> existingTherapyOpt = therapyRepository.findById(therapy.getId());

        if (existingTherapyOpt.isPresent()) {
            Therapy existingTherapy = existingTherapyOpt.get();

            // 保留原有的创建时间
            therapy.setCreateTime(existingTherapy.getCreateTime());
            // 更新时间设为当前时间
            therapy.setUpdateTime(LocalDateTime.now());

            return therapyRepository.save(therapy);
        } else {
            throw new RuntimeException("治疗方法不存在，ID: " + therapy.getId());
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteTherapy(Long id) {
        if (therapyRepository.existsById(id)) {
            therapyRepository.deleteById(id);
        } else {
            throw new RuntimeException("治疗方法不存在，ID: " + id);
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public List<Therapy> saveAllTherapies(List<Therapy> therapies) {
        LocalDateTime now = LocalDateTime.now();

        // 设置创建时间和更新时间
        therapies.forEach(therapy -> {
            therapy.setCreateTime(now);
            therapy.setUpdateTime(now);
        });

        return therapyRepository.saveAll(therapies);
    }

    @Override
    public Map<String, Long> countTherapiesByCategory() {
        List<Therapy> allTherapies = therapyRepository.findAll();
        Map<String, Long> categoryCounts = new HashMap<>(allTherapies.size());

        // 按类别统计治疗方法数量
        for (Therapy therapy : allTherapies) {
            String category = therapy.getCategory();
            if (category != null) {
                categoryCounts.put(category, categoryCounts.getOrDefault(category, 0L) + 1);
            }
        }

        return categoryCounts;
    }

    @Override
    public boolean existsTherapyById(Long id) {
        return therapyRepository.existsById(id);
    }

    @Override
    public List<String> findAllTherapyCategories() {
        return therapyRepository.findAll().stream()
                .map(Therapy::getCategory)
                .filter(category -> category != null && !category.isEmpty())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllTherapyApproaches() {
        return therapyRepository.findAll().stream()
                .map(Therapy::getApproach)
                .filter(approach -> approach != null && !approach.isEmpty())
                .distinct()
                .collect(Collectors.toList());
    }
}
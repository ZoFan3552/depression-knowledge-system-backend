package com.zeddic.service.graph.depression.impl;

import com.zeddic.entity.graph.depression.*;
import com.zeddic.repository.graph.DiseaseRepository;
import com.zeddic.service.graph.depression.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Zeddic
 */
@Service
public class DiseaseServiceImpl implements DiseaseService {
    private final DiseaseRepository diseaseRepository;

    @Autowired
    public DiseaseServiceImpl(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Disease saveDisease(Disease disease) {
        // 设置创建时间和更新时间
        LocalDateTime now = LocalDateTime.now();
        disease.setCreateTime(now);
        disease.setUpdateTime(now);

        // 如果同义词为空，则初始化
        if (disease.getSynonyms() == null) {
            disease.setSynonyms(new HashSet<>());
        }

        // 如果关系集合为空，则初始化
        if (disease.getSymptoms() == null) {
            disease.setSymptoms(new HashSet<>());
        }
        if (disease.getRiskFactors() == null) {
            disease.setRiskFactors(new HashSet<>());
        }
        if (disease.getTreatments() == null) {
            disease.setTreatments(new HashSet<>());
        }
        if (disease.getMedications() == null) {
            disease.setMedications(new HashSet<>());
        }

        return diseaseRepository.save(disease);
    }

    @Override
    public Optional<Disease> findDiseaseById(Long id) {
        return diseaseRepository.findById(id);
    }

    @Override
    public Optional<Disease> findDiseaseByName(String name) {
        return diseaseRepository.findByName(name);
    }

    @Override
    public Optional<Disease> findDiseaseByMedicalCode(String medicalCode) {
        return diseaseRepository.findByMedicalCode(medicalCode);
    }

    @Override
    public List<Disease> findAllDiseases() {
        return diseaseRepository.findAll();
    }

    @Override
    public List<Disease> findDiseasesByCategory(String category) {
        return diseaseRepository.findByCategory(category);
    }

    @Override
    public List<Disease> findDiseasesBySymptomName(String symptomName) {
        return diseaseRepository.findBySymptomName(symptomName);
    }

    @Override
    public List<Disease> findDiseasesByTherapyName(String therapyName) {
        return diseaseRepository.findByTherapyName(therapyName);
    }

    @Override
    public List<Disease> findDiseasesByMedicationName(String medicationName) {
        return diseaseRepository.findByMedicationName(medicationName);
    }

    @Override
    public List<Disease> findDiseasesByRiskFactorName(String riskName) {
        return diseaseRepository.findByRiskFactorName(riskName);
    }

    @Override
    public List<Disease> findDiseasesBySynonym(String synonym) {
        return diseaseRepository.findBySynonym(synonym);
    }

    @Override
    public List<Disease> findDiseasesByPrevalenceRateBetween(Double minRate, Double maxRate) {
        return diseaseRepository.findByPrevalenceRateBetween(minRate, maxRate);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Disease updateDisease(Disease disease) {
        Optional<Disease> existingDiseaseOpt = diseaseRepository.findById(disease.getId());

        if (existingDiseaseOpt.isPresent()) {
            Disease existingDisease = existingDiseaseOpt.get();

            // 保留原有的创建时间
            disease.setCreateTime(existingDisease.getCreateTime());
            // 更新时间设为当前时间
            disease.setUpdateTime(LocalDateTime.now());

            // 保留关系数据，避免丢失
            if (disease.getSymptoms() == null && existingDisease.getSymptoms() != null) {
                disease.setSymptoms(existingDisease.getSymptoms());
            }
            if (disease.getRiskFactors() == null && existingDisease.getRiskFactors() != null) {
                disease.setRiskFactors(existingDisease.getRiskFactors());
            }
            if (disease.getTreatments() == null && existingDisease.getTreatments() != null) {
                disease.setTreatments(existingDisease.getTreatments());
            }
            if (disease.getMedications() == null && existingDisease.getMedications() != null) {
                disease.setMedications(existingDisease.getMedications());
            }
            if (disease.getSynonyms() == null && existingDisease.getSynonyms() != null) {
                disease.setSynonyms(existingDisease.getSynonyms());
            }

            return diseaseRepository.save(disease);
        } else {
            throw new RuntimeException("疾病不存在，ID: " + disease.getId());
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Disease addSymptomsToDisease(Long diseaseId, Set<Symptom> symptoms) {
        Disease disease = diseaseRepository.findById(diseaseId)
                .orElseThrow(() -> new RuntimeException("疾病不存在，ID: " + diseaseId));

        if (disease.getSymptoms() == null) {
            disease.setSymptoms(new HashSet<>());
        }
        disease.getSymptoms().addAll(symptoms);
        disease.setUpdateTime(LocalDateTime.now());

        return diseaseRepository.save(disease);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Disease addTherapiesToDisease(Long diseaseId, Set<Therapy> therapies) {
        Disease disease = diseaseRepository.findById(diseaseId)
                .orElseThrow(() -> new RuntimeException("疾病不存在，ID: " + diseaseId));

        if (disease.getTreatments() == null) {
            disease.setTreatments(new HashSet<>());
        }
        disease.getTreatments().addAll(therapies);
        disease.setUpdateTime(LocalDateTime.now());

        return diseaseRepository.save(disease);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Disease addMedicationsToDisease(Long diseaseId, Set<Medication> medications) {
        Disease disease = diseaseRepository.findById(diseaseId)
                .orElseThrow(() -> new RuntimeException("疾病不存在，ID: " + diseaseId));

        if (disease.getMedications() == null) {
            disease.setMedications(new HashSet<>());
        }
        disease.getMedications().addAll(medications);
        disease.setUpdateTime(LocalDateTime.now());

        return diseaseRepository.save(disease);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Disease addRiskFactorsToDisease(Long diseaseId, Set<Risk> riskFactors) {
        Disease disease = diseaseRepository.findById(diseaseId)
                .orElseThrow(() -> new RuntimeException("疾病不存在，ID: " + diseaseId));

        if (disease.getRiskFactors() == null) {
            disease.setRiskFactors(new HashSet<>());
        }
        disease.getRiskFactors().addAll(riskFactors);
        disease.setUpdateTime(LocalDateTime.now());

        return diseaseRepository.save(disease);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Disease addSynonymsToDisease(Long diseaseId, Set<String> synonyms) {
        Disease disease = diseaseRepository.findById(diseaseId)
                .orElseThrow(() -> new RuntimeException("疾病不存在，ID: " + diseaseId));

        if (disease.getSynonyms() == null) {
            disease.setSynonyms(new HashSet<>());
        }
        disease.getSynonyms().addAll(synonyms);
        disease.setUpdateTime(LocalDateTime.now());

        return diseaseRepository.save(disease);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteDisease(Long id) {
        if (diseaseRepository.existsById(id)) {
            diseaseRepository.deleteById(id);
        } else {
            throw new RuntimeException("疾病不存在，ID: " + id);
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Disease removeSymptomFromDisease(Long diseaseId, Long symptomId) {
        Disease disease = diseaseRepository.findById(diseaseId)
                .orElseThrow(() -> new RuntimeException("疾病不存在，ID: " + diseaseId));

        if (disease.getSymptoms() != null) {
            disease.setSymptoms(disease.getSymptoms().stream()
                    .filter(s -> !s.getId().equals(symptomId))
                    .collect(Collectors.toSet()));
            disease.setUpdateTime(LocalDateTime.now());
            return diseaseRepository.save(disease);
        }

        return disease;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Disease removeTherapyFromDisease(Long diseaseId, Long therapyId) {
        Disease disease = diseaseRepository.findById(diseaseId)
                .orElseThrow(() -> new RuntimeException("疾病不存在，ID: " + diseaseId));

        if (disease.getTreatments() != null) {
            disease.setTreatments(disease.getTreatments().stream()
                    .filter(t -> !t.getId().equals(therapyId))
                    .collect(Collectors.toSet()));
            disease.setUpdateTime(LocalDateTime.now());
            return diseaseRepository.save(disease);
        }

        return disease;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Disease removeMedicationFromDisease(Long diseaseId, Long medicationId) {
        Disease disease = diseaseRepository.findById(diseaseId)
                .orElseThrow(() -> new RuntimeException("疾病不存在，ID: " + diseaseId));

        if (disease.getMedications() != null) {
            disease.setMedications(disease.getMedications().stream()
                    .filter(m -> !m.getId().equals(medicationId))
                    .collect(Collectors.toSet()));
            disease.setUpdateTime(LocalDateTime.now());
            return diseaseRepository.save(disease);
        }

        return disease;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Disease removeRiskFactorFromDisease(Long diseaseId, Long riskId) {
        Disease disease = diseaseRepository.findById(diseaseId)
                .orElseThrow(() -> new RuntimeException("疾病不存在，ID: " + diseaseId));

        if (disease.getRiskFactors() != null) {
            disease.setRiskFactors(disease.getRiskFactors().stream()
                    .filter(r -> !r.getId().equals(riskId))
                    .collect(Collectors.toSet()));
            disease.setUpdateTime(LocalDateTime.now());
            return diseaseRepository.save(disease);
        }

        return disease;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Disease removeSynonymFromDisease(Long diseaseId, String synonym) {
        Disease disease = diseaseRepository.findById(diseaseId)
                .orElseThrow(() -> new RuntimeException("疾病不存在，ID: " + diseaseId));

        if (disease.getSynonyms() != null) {
            disease.getSynonyms().remove(synonym);
            disease.setUpdateTime(LocalDateTime.now());
            return diseaseRepository.save(disease);
        }

        return disease;
    }
}

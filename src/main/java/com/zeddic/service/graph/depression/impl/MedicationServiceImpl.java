package com.zeddic.service.graph.depression.impl;

import com.zeddic.entity.graph.depression.Medication;
import com.zeddic.repository.graph.MedicationRepository;
import com.zeddic.service.graph.depression.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Zeddic
 */
@Service
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationServiceImpl(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Medication saveMedication(Medication medication) {
        // 设置创建时间和更新时间
        LocalDateTime now = LocalDateTime.now();
        medication.setCreateTime(now);
        medication.setUpdateTime(now);

        // 初始化集合类字段，避免空指针异常
        if (medication.getBrandNames() == null) {
            medication.setBrandNames(new ArrayList<>());
        }
        if (medication.getSideEffects() == null) {
            medication.setSideEffects(new HashSet<>());
        }
        if (medication.getContraindications() == null) {
            medication.setContraindications(new HashSet<>());
        }
        if (medication.getInteractions() == null) {
            medication.setInteractions(new HashSet<>());
        }

        return medicationRepository.save(medication);
    }

    @Override
    public Optional<Medication> findMedicationById(Long id) {
        return medicationRepository.findById(id);
    }

    @Override
    public Optional<Medication> findMedicationByName(String name) {
        return medicationRepository.findByName(name);
    }

    @Override
    public Optional<Medication> findMedicationByGenericName(String genericName) {
        return medicationRepository.findByGenericName(genericName);
    }

    @Override
    public List<Medication> findMedicationsByBrandName(String brandName) {
        return medicationRepository.findByBrandName(brandName);
    }

    @Override
    public List<Medication> findAllMedications() {
        return medicationRepository.findAll();
    }

    @Override
    public List<Medication> findMedicationsByDrugClass(String drugClass) {
        return medicationRepository.findByDrugClass(drugClass);
    }

    @Override
    public List<Medication> findMedicationsByAdministrationRoute(String route) {
        return medicationRepository.findByAdministrationRoute(route);
    }

    @Override
    public List<Medication> findMedicationsBySideEffect(String sideEffect) {
        return medicationRepository.findBySideEffect(sideEffect);
    }

    @Override
    public List<Medication> findMedicationsByContraindication(String contraindication) {
        return medicationRepository.findByContraindication(contraindication);
    }

    @Override
    public List<Medication> findMedicationsByInteraction(String interaction) {
        return medicationRepository.findByInteraction(interaction);
    }

    @Override
    public List<Medication> findMedicationsByMechanismContaining(String mechanismKeyword) {
        return medicationRepository.findByMechanismContaining(mechanismKeyword);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Medication updateMedication(Medication medication) {
        Optional<Medication> existingMedicationOpt = medicationRepository.findById(medication.getId());

        if (existingMedicationOpt.isPresent()) {
            Medication existingMedication = existingMedicationOpt.get();

            // 保留原有的创建时间
            medication.setCreateTime(existingMedication.getCreateTime());
            // 更新时间设为当前时间
            medication.setUpdateTime(LocalDateTime.now());

            // 保留集合类数据，避免丢失
            if (medication.getBrandNames() == null && existingMedication.getBrandNames() != null) {
                medication.setBrandNames(existingMedication.getBrandNames());
            }
            if (medication.getSideEffects() == null && existingMedication.getSideEffects() != null) {
                medication.setSideEffects(existingMedication.getSideEffects());
            }
            if (medication.getContraindications() == null && existingMedication.getContraindications() != null) {
                medication.setContraindications(existingMedication.getContraindications());
            }
            if (medication.getInteractions() == null && existingMedication.getInteractions() != null) {
                medication.setInteractions(existingMedication.getInteractions());
            }

            return medicationRepository.save(medication);
        } else {
            throw new RuntimeException("药物不存在，ID: " + medication.getId());
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Medication addBrandNamesToMedication(Long medicationId, List<String> brandNames) {
        Medication medication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new RuntimeException("药物不存在，ID: " + medicationId));

        if (medication.getBrandNames() == null) {
            medication.setBrandNames(new ArrayList<>());
        }
        medication.getBrandNames().addAll(brandNames);
        medication.setUpdateTime(LocalDateTime.now());

        return medicationRepository.save(medication);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Medication addSideEffectsToMedication(Long medicationId, Set<String> sideEffects) {
        Medication medication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new RuntimeException("药物不存在，ID: " + medicationId));

        if (medication.getSideEffects() == null) {
            medication.setSideEffects(new HashSet<>());
        }
        medication.getSideEffects().addAll(sideEffects);
        medication.setUpdateTime(LocalDateTime.now());

        return medicationRepository.save(medication);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Medication addContraindicationsToMedication(Long medicationId, Set<String> contraindications) {
        Medication medication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new RuntimeException("药物不存在，ID: " + medicationId));

        if (medication.getContraindications() == null) {
            medication.setContraindications(new HashSet<>());
        }
        medication.getContraindications().addAll(contraindications);
        medication.setUpdateTime(LocalDateTime.now());

        return medicationRepository.save(medication);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Medication addInteractionsToMedication(Long medicationId, Set<String> interactions) {
        Medication medication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new RuntimeException("药物不存在，ID: " + medicationId));

        if (medication.getInteractions() == null) {
            medication.setInteractions(new HashSet<>());
        }
        medication.getInteractions().addAll(interactions);
        medication.setUpdateTime(LocalDateTime.now());

        return medicationRepository.save(medication);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteMedication(Long id) {
        if (medicationRepository.existsById(id)) {
            medicationRepository.deleteById(id);
        } else {
            throw new RuntimeException("药物不存在，ID: " + id);
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Medication removeBrandNameFromMedication(Long medicationId, String brandName) {
        Medication medication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new RuntimeException("药物不存在，ID: " + medicationId));

        if (medication.getBrandNames() != null) {
            medication.getBrandNames().remove(brandName);
            medication.setUpdateTime(LocalDateTime.now());
            return medicationRepository.save(medication);
        }

        return medication;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Medication removeSideEffectFromMedication(Long medicationId, String sideEffect) {
        Medication medication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new RuntimeException("药物不存在，ID: " + medicationId));

        if (medication.getSideEffects() != null) {
            medication.getSideEffects().remove(sideEffect);
            medication.setUpdateTime(LocalDateTime.now());
            return medicationRepository.save(medication);
        }

        return medication;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Medication removeContraindicationFromMedication(Long medicationId, String contraindication) {
        Medication medication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new RuntimeException("药物不存在，ID: " + medicationId));

        if (medication.getContraindications() != null) {
            medication.getContraindications().remove(contraindication);
            medication.setUpdateTime(LocalDateTime.now());
            return medicationRepository.save(medication);
        }

        return medication;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Medication removeInteractionFromMedication(Long medicationId, String interaction) {
        Medication medication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new RuntimeException("药物不存在，ID: " + medicationId));

        if (medication.getInteractions() != null) {
            medication.getInteractions().remove(interaction);
            medication.setUpdateTime(LocalDateTime.now());
            return medicationRepository.save(medication);
        }

        return medication;
    }
}
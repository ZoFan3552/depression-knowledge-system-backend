package com.zeddic.service.knowledge;

import com.zeddic.entity.knowledge.Medication;
import com.zeddic.repository.knowledge.MedicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author: zeddic
 * @description:
 * @date: 2025/1/19 下午6:21
 */
@Service
public class MedicationService {
    private final MedicationRepository medicationRepository;

    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public void addMedicationNode(String  medication) {
        medicationRepository.save(new Medication(medication));
    }

    public List<Medication> getAllMedicationNodes() {
        return medicationRepository.findAll();
    }

    public Optional<Medication> getMedicationNodeByName(String name) {
        return medicationRepository.findById(name);
    }

    public void deleteAllMedicationNodes() {
        medicationRepository.deleteAll();
    }

    public void deleteMedicationNodeByName(String name) {
        medicationRepository.deleteById(name);
    }
}

package com.zeddic.service.knowledge;

import com.zeddic.entity.knowledge.*;
import com.zeddic.repository.knowledge.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author: zeddic
 * @description:
 * @date: 2025/1/19 下午5:44
 */
@Service
@Transactional
@Slf4j
public class DepressionService {
    private final DepressionRepository depressionRepository;

    public DepressionService(DepressionRepository depressionRepository, CauseRepository causeRepository, DiagnosisRepository diagnosisRepository, MedicationRepository medicationRepository, PreventionRepository preventionRepository, SymptomRepository symptomRepository, TreatmentRepository treatmentRepository) {
        this.depressionRepository = depressionRepository;
    }

    public void addDepressionNode(String depressionName) {
        depressionRepository.save(new Depression(depressionName));
    }

    public List<Depression> getAllDepressionNodes() {
        return depressionRepository.findAll();
    }

    public Optional<Depression> getDepressionNodeByName(String name) {
        return depressionRepository.findById(name);
    }

    public void deleteAllDepressionNodes() {
        depressionRepository.deleteAll();
    }

    public void deleteDepressionNodeByName(String name) {
        depressionRepository.deleteById(name);
    }

    public void addCauseRelation(String depressionName, String causeName) {
        depressionRepository.addCauseRelation(depressionName, causeName);
    }

    public void deleteCauseRelation(String depressionName, String causeName) {
        depressionRepository.deleteCauseRelation(depressionName, causeName);
    }


    public void addDiagnosisRelation(String depressionName, String diagnosisName) {
        depressionRepository.addDiagnosisRelation(depressionName, diagnosisName);
    }

    public void deleteDiagnosisRelation(String depressionName, String diagnosisName) {
        depressionRepository.deleteDiagnosisRelation(depressionName, diagnosisName);
    }

    public void addMedicationRelation(String depressionName, String medicationName) {
        depressionRepository.addMedicationRelation(depressionName, medicationName);
    }

    public void deleteMedicationRelation(String depressionName, String medicationName) {
        depressionRepository.deleteMedicationRelation(depressionName, medicationName);
    }

    public void addPreventionRelation(String depressionName, String preventionName) {
        depressionRepository.addPreventionRelation(depressionName, preventionName);
    }

    public void deletePreventionRelation(String depressionName, String preventionName) {
        depressionRepository.deletePreventionRelation(depressionName, preventionName);
    }

    public void addSymptomRelation(String depressionName, String symptomName) {
        depressionRepository.addSymptomRelation(depressionName, symptomName);
    }

    public void deleteSymptomRelation(String depressionName, String symptomName) {
        depressionRepository.deleteSymptomRelation(depressionName, symptomName);
    }

    public void addTreatmentRelation(String depressionName, String treatmentName) {
        depressionRepository.addTreatmentRelation(depressionName, treatmentName);
    }

    public void deleteTreatmentRelation(String depressionName, String treatmentName) {
        depressionRepository.deleteTreatmentRelation(depressionName, treatmentName);
    }
}

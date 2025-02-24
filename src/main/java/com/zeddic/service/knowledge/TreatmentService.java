package com.zeddic.service.knowledge;

import com.zeddic.entity.knowledge.Treatment;
import com.zeddic.repository.knowledge.TreatmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author: zeddic
 * @description:
 * @date: 2025/1/19 下午6:25
 */
@Service
public class TreatmentService {
    private final TreatmentRepository treatmentRepository;

    public TreatmentService(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    public void addTreatmentNode(String treatment) {
        treatmentRepository.save(new Treatment(treatment));
    }

    public List<Treatment> getAllTreatmentNodes() {
        return treatmentRepository.findAll();
    }

    public Optional<Treatment> getTreatmentNodeByName(String name) {
        return treatmentRepository.findById(name);
    }

    public void deleteAllTreatmentNodes() {
        treatmentRepository.deleteAll();
    }

    public void deleteTreatmentNodeByName(String name) {
        treatmentRepository.deleteById(name);
    }
}

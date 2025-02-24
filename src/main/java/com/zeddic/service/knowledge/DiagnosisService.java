package com.zeddic.service.knowledge;

import com.zeddic.entity.knowledge.Diagnosis;
import com.zeddic.repository.knowledge.DiagnosisRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author: zeddic
 * @description:
 * @date: 2025/1/19 下午6:20
 */
@Service
public class DiagnosisService {
    private final DiagnosisRepository diagnosisRepository;

    public DiagnosisService(DiagnosisRepository diagnosisRepository) {
        this.diagnosisRepository = diagnosisRepository;
    }

    public void addDiagnosisNode(String diagnosis) {
        diagnosisRepository.save(new Diagnosis(diagnosis));
    }

    public List<Diagnosis> getAllDiagnosisNodes() {
        return diagnosisRepository.findAll();
    }

    public Optional<Diagnosis> getDiagnosisById(String id) {
        return diagnosisRepository.findById(id);
    }

    public void deleteAllDiagnosisNodes() {
        diagnosisRepository.deleteAll();
    }

    public void deleteDiagnosisNodeByName(String name) {
        diagnosisRepository.deleteById(name);
    }
}

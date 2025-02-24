package com.zeddic.service.knowledge;

import com.zeddic.entity.knowledge.Symptom;
import com.zeddic.repository.knowledge.SymptomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author: zeddic
 * @description:
 * @date: 2025/1/19 下午6:25
 */
@Service
public class SymptomService {
    private final SymptomRepository symptomRepository;

    public SymptomService(SymptomRepository symptomRepository) {
        this.symptomRepository = symptomRepository;
    }

    public void addSymptomNode(String symptom) {
        symptomRepository.save(new Symptom(symptom));
    }

    public List<Symptom> getAllSymptomNodes() {
        return symptomRepository.findAll();
    }

    public Optional<Symptom> getSymptomNodeByName(String name) {
        return symptomRepository.findById(name);
    }

    public void deleteAllSymptomNodes() {
        symptomRepository.deleteAll();
    }

    public void deleteSymptomNodeByName(String name) {
        symptomRepository.deleteById(name);
    }
}

package com.zeddic.service.knowledge;

import com.zeddic.entity.knowledge.Prevention;
import com.zeddic.repository.knowledge.PreventionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author: zeddic
 * @description:
 * @date: 2025/1/19 下午6:23
 */
@Service
public class PreventionService {
    private final PreventionRepository preventionRepository;

    public PreventionService(PreventionRepository preventionRepository) {
        this.preventionRepository = preventionRepository;
    }

    public void addPreventionNode(String  prevention) {
        preventionRepository.save(new Prevention(prevention));
    }

    public List<Prevention> getAllPreventionNodes() {
        return preventionRepository.findAll();
    }

    public Optional<Prevention> getPreventionNodeByName(String  name) {
        return preventionRepository.findById(name);
    }

    public void deleteAllPreventionNodes() {
        preventionRepository.deleteAll();
    }

    public void deletePreventionNodeByName(String name) {
        preventionRepository.deleteById(name);
    }
}

package com.zeddic.service.knowledge;

import com.zeddic.entity.knowledge.Cause;
import com.zeddic.repository.knowledge.CauseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author: zeddic
 * @description:
 * @date: 2025/1/19 下午6:20
 */
@Service
public class CauseService {
    private final CauseRepository causeRepository;

    public CauseService(CauseRepository causeRepository) {
        this.causeRepository = causeRepository;
    }

    public void addCauseNode(String cause) {
        causeRepository.save(new Cause(cause));
    }

    public List<Cause> getAllCausesNodes() {
        return causeRepository.findAll();
    }

    public Optional<Cause> getCauseNodeByName(String name) {
        return causeRepository.findById(name);
    }

    public void deleteAllCauseNodes(){
        causeRepository.deleteAll();
    }

    public void deleteCauseNodeByName(String name) {
        causeRepository.deleteById(name);
    }
}

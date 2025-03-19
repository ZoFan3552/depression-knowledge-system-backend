package com.zeddic.service.graph.depression;

import com.zeddic.repository.graph.DiseaseRepository;
import com.zeddic.repository.graph.RiskRepository;
import org.springframework.stereotype.Service;

@Service
public class RiskService {
    private final RiskRepository riskRepository;
    private final DiseaseRepository diseaseRepository;

    public RiskService(
            RiskRepository riskRepository,
            DiseaseRepository diseaseRepository
    ) {
        this.riskRepository = riskRepository;
        this.diseaseRepository = diseaseRepository;
    }
}

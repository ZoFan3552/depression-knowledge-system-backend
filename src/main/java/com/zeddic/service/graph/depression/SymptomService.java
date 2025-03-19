package com.zeddic.service.graph.depression;

import com.zeddic.repository.graph.DiseaseRepository;
import com.zeddic.repository.graph.SymptomRepository;
import org.springframework.stereotype.Service;

@Service
public class SymptomService {
    private final SymptomRepository symptomRepository;
    private final DiseaseRepository diseaseRepository;

    public SymptomService(
            SymptomRepository symptomRepository,
            DiseaseRepository diseaseRepository
    ) {
        this.symptomRepository = symptomRepository;
        this.diseaseRepository = diseaseRepository;
    }
}

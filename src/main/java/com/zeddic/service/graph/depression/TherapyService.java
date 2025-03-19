package com.zeddic.service.graph.depression;

import com.zeddic.repository.graph.DiseaseRepository;
import com.zeddic.repository.graph.TherapyRepository;
import org.springframework.stereotype.Service;

@Service
public class TherapyService {
    private final TherapyRepository therapyRepository;
    private final DiseaseRepository diseaseRepository;

    public TherapyService(
            TherapyRepository therapyRepository,
            DiseaseRepository diseaseRepository
    ) {
        this.therapyRepository = therapyRepository;
        this.diseaseRepository = diseaseRepository;
    }
}

package com.zeddic.service.graph.depression;

import com.zeddic.repository.graph.*;
import org.springframework.stereotype.Service;

@Service
public class DiseaseService {
    private final DiseaseRepository diseaseRepository;
    private final SymptomRepository symptomRepository;
    private final RiskRepository riskRepository;
    private final MedicationRepository medicationRepository;
    private final TherapyRepository therapyRepository;

    public DiseaseService(
            DiseaseRepository diseaseRepository,
            SymptomRepository symptomRepository,
            RiskRepository riskRepository,
            MedicationRepository medicationRepository,
            TherapyRepository therapyRepository
    ) {
        this.diseaseRepository = diseaseRepository;
        this.symptomRepository = symptomRepository;
        this.riskRepository = riskRepository;
        this.medicationRepository = medicationRepository;
        this.therapyRepository = therapyRepository;
    }


}

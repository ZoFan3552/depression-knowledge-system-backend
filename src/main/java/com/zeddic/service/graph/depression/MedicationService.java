package com.zeddic.service.graph.depression;

import com.zeddic.repository.graph.DiseaseRepository;
import com.zeddic.repository.graph.MedicationRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicationService {
    private final MedicationRepository medicationRepository;
    private final DiseaseRepository diseaseRepository;

    public MedicationService(
            MedicationRepository medicationRepository,
            DiseaseRepository diseaseRepository
    ) {
        this.medicationRepository = medicationRepository;
        this.diseaseRepository = diseaseRepository;
    }
}

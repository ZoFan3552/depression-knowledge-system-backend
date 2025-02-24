package com.zeddic.knowledge;

import com.zeddic.entity.knowledge.*;
import com.zeddic.service.knowledge.*;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
class KnowledgeServiceTest {

    @Resource
    private CauseService causeService;
    @Resource
    private DiagnosisService diagnosisService;
    @Resource
    private DepressionService depressionService;
    @Resource
    private MedicationService medicationService;
    @Resource
    private PreventionService preventionService;
    @Resource
    private SymptomService symptomService;
    @Resource
    private TreatmentService treatmentService;

    //@Test
//    void testInsertDisease() {
//        Cause cause = new Cause("遗传");
//        HashSet<Cause> causes = new HashSet<>();
//        causes.add(cause);
//        log.info("cause 添加成功：{}", causeService.addCauseNode(cause));
//        Diagnosis diagnosis = new Diagnosis("PHQ-9 量表");
//        HashSet<Diagnosis> diagnoses = new HashSet<>();
//        diagnoses.add(diagnosis);
//        log.info("diagnosis 添加成功：{}", diagnosisService.addDiagnosisNode(diagnosis));
//        Medication medication = new Medication("氟西汀");
//        log.info("medication 添加成功：{}", medicationService.addMedicationNode(medication));
//        HashSet<Medication> medications = new HashSet<>();
//        medications.add(medication);
//        Prevention prevention = new Prevention("健康生活方式");
//        log.info("prevention 添加成功：{}", preventionService.addPreventionNode(prevention));
//        HashSet<Prevention> preventions = new HashSet<>();
//        preventions.add(prevention);
//        Symptom symptom = new Symptom("睡眠障碍");
//        log.info("symptom 添加成功：{}", symptomService.addSymptomNode(symptom));
//        HashSet<Symptom> symptoms = new HashSet<>();
//        symptoms.add(symptom);
//        Treatment treatment = new Treatment("认知行为疗法");
//        log.info("treatment 添加成功：{}", treatmentService.addTreatmentNode(treatment));
//        HashSet<Treatment> treatments = new HashSet<>();
//        treatments.add(treatment);
//        Depression depression = Depression.builder()
//                .name("重度抑郁症")
//                .description("一种非常严重的疾病")
//                .causes(causes)
//                .diagnoses(diagnoses)
//                .medications(medications)
//                .preventions(preventions)
//                .symptoms(symptoms)
//                .treatments(treatments)
//                .build();
//        log.info("depression 添加成功：{}", depressionService.addDepressionNode(depression));
//    }

    @Test
    void testDiseaseService(){
//        List<Disease> allDiseases = diseaseService.getAllDiseasesNodes();
        Optional<Depression> disease = depressionService.getDepressionNodeByName("重度抑郁症");
        log.info("当前抑郁症疾病:{}", disease);
    }

    @Test
    void testCauseService(){
        List<Cause> allCauses = causeService.getAllCausesNodes();
        log.info("获取所有病因:{}", allCauses);
    }

    @Test
    void testDiagnosisService(){
        List<Diagnosis> allDiagnosis = diagnosisService.getAllDiagnosisNodes();
        log.info("获取所有诊断:{}", allDiagnosis);
    }

    @Test
    void testMedicationService(){
        List<Medication> allMedications = medicationService.getAllMedicationNodes();
        log.info("获取所有药物:{}", allMedications);
    }

    @Test
    void testPreventionService(){
        List<Prevention> allPreventions = preventionService.getAllPreventionNodes();
        log.info("获取所有预防措施:{}", allPreventions);
    }

    @Test
    void testSymptomService(){
        List<Symptom> allSymptoms = symptomService.getAllSymptomNodes();
        log.info("获取所有症状:{}", allSymptoms);
    }

    @Test
    void testTreatmentService(){
        List<Treatment> allTreatments = treatmentService.getAllTreatmentNodes();
        log.info("获取所有治疗措施:{}", allTreatments);
    }
}

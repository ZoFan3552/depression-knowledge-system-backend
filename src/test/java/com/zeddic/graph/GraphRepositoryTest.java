package com.zeddic.graph;

import com.zeddic.entity.graph.depression.*;
import com.zeddic.repository.graph.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@SpringBootTest
@Transactional
@Slf4j
public class GraphRepositoryTest {

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Autowired
    private SymptomRepository symptomRepository;

    @Autowired
    private RiskRepository riskRepository;

    @Autowired
    private TherapyRepository therapyRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    private Disease testDisease;
    private Symptom testSymptom;
    private Risk testRisk;
    private Therapy testTherapy;
    private Medication testMedication;

    @BeforeEach
    void setUp() {
        // 清理测试数据
        diseaseRepository.deleteAll();
        symptomRepository.deleteAll();
        riskRepository.deleteAll();
        therapyRepository.deleteAll();
        medicationRepository.deleteAll();

        // 创建测试数据
        LocalDateTime now = LocalDateTime.now();

        // 创建症状
        testSymptom = Symptom.builder()
                .category("情绪")
                .severity(8)
                .duration("持续两周以上")
                .isCommon(true)
                .manifestations(new HashSet<>(Arrays.asList("情绪低落", "兴趣丧失")))
                .build();
        testSymptom.setName("抑郁情绪");
        testSymptom.setDescription("持续的悲伤或空虚感");
        testSymptom.setCreateTime(now);
        testSymptom.setUpdateTime(now);

        // 创建风险因素
        testRisk = Risk.builder()
                .category("心理因素")
                .impactFactor(0.7)
                .evidenceLevel("A")
                .preventiveMeasures(new HashSet<>(Arrays.asList("心理咨询", "压力管理")))
                .build();
        testRisk.setName("慢性压力");
        testRisk.setDescription("长期面临压力和挑战");
        testRisk.setCreateTime(now);
        testRisk.setUpdateTime(now);

        // 创建治疗方法
        testTherapy = Therapy.builder()
                .category("心理治疗")
                .approach("认知行为疗法")
                .durationCourse("12-16周")
                .sideEffects("无明显副作用")
                .build();
        testTherapy.setName("CBT");
        testTherapy.setDescription("帮助患者识别并改变消极思维模式");
        testTherapy.setCreateTime(now);
        testTherapy.setUpdateTime(now);

        // 创建药物
        testMedication = Medication.builder()
                .genericName("氟西汀")
                .brandNames(Arrays.asList("百忧解", "Prozac"))
                .drugClass("SSRI")
                .mechanism("选择性5-羟色胺再摄取抑制剂")
                .dosage("20-60mg/天")
                .administrationRoute("口服")
                .sideEffects(new HashSet<>(Arrays.asList("恶心", "头痛", "失眠")))
                .contraindications(new HashSet<>(List.of("MAO抑制剂同时使用")))
                .interactions(new HashSet<>(Arrays.asList("华法林", "锂剂")))
                .build();
        testMedication.setName("氟西汀");
        testMedication.setDescription("一种常用于治疗抑郁症的SSRI类药物");
        testMedication.setCreateTime(now);
        testMedication.setUpdateTime(now);

        // 创建疾病
        testDisease = Disease.builder()
                .medicalCode("F32.1")
                .category("中度抑郁症")
                .synonyms(new HashSet<>(Arrays.asList("抑郁障碍", "情感障碍")))
                .prevalenceRate(0.06)
                .diagnosticCriteria("符合ICD-10中度抑郁症诊断标准")
                .build();
        testDisease.setName("中度抑郁症");
        testDisease.setDescription("一种常见的心理障碍，表现为持续的情绪低落和兴趣丧失");
        testDisease.setCreateTime(now);
        testDisease.setUpdateTime(now);
    }

    @Test
    void testSaveAndFindDisease() {
        // 保存疾病
        Disease savedDisease = diseaseRepository.save(testDisease);

        // 验证保存成功
        Assertions.assertNotNull(savedDisease.getId());

        // 查找疾病
        Optional<Disease> foundDisease = diseaseRepository.findById(savedDisease.getId());

        // 验证查找结果
        Assertions.assertTrue(foundDisease.isPresent());
        Assertions.assertEquals(testDisease.getName(), foundDisease.get().getName());
        Assertions.assertEquals(testDisease.getMedicalCode(), foundDisease.get().getMedicalCode());
        Assertions.assertEquals(testDisease.getCategory(), foundDisease.get().getCategory());
    }
}

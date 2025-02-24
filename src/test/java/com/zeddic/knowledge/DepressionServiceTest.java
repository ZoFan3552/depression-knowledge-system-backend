package com.zeddic.knowledge;

import com.zeddic.entity.knowledge.Depression;
import com.zeddic.service.knowledge.DepressionService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class DepressionServiceTest {
    private static final Logger log = LoggerFactory.getLogger(DepressionServiceTest.class);
    @Autowired
    private DepressionService depressionService;

    @Test
    void testAddMethod(){
        depressionService.addDepressionNode("重度抑郁症");
        depressionService.addCauseRelation("重度抑郁症", "遗传");
        depressionService.addSymptomRelation("重度抑郁症", "失眠");
        depressionService.addTreatmentRelation("重度抑郁症", "电击治疗");
        depressionService.addPreventionRelation("重度抑郁症", "积极运动");
        depressionService.addMedicationRelation("重度抑郁症", "氟西汀");
        depressionService.addDiagnosisRelation("重度抑郁症", "PHQ-9测量表");
    }

    @Test
    void testAddMethod2(){
        depressionService.addDepressionNode("轻度抑郁症");
        depressionService.addCauseRelation("轻度抑郁症", "遗传");
        depressionService.addSymptomRelation("轻度抑郁症", "失眠");
        depressionService.addTreatmentRelation("轻度抑郁症", "电击治疗");
        depressionService.addPreventionRelation("轻度抑郁症", "积极运动");
        depressionService.addMedicationRelation("轻度抑郁症", "氟西汀");
        depressionService.addDiagnosisRelation("轻度抑郁症", "PHQ-9测量表");
    }

    @Test
    void testDeleteMethod(){
        depressionService.deleteDepressionNodeByName("轻度抑郁症");
    }


}

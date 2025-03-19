package com.zeddic.entity.graph.depression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * 疾病实体类，表示抑郁症及其分类
 */
@Node
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Disease{
    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "name")
    private String name;        // 实体名称

    @Property(name = "description")
    private String description; // 实体描述

    
    @Property(name = "createTime")
    private LocalDateTime createTime;    // 创建时间

    
    @Property(name = "updateTime")
    private LocalDateTime updateTime;    // 更新时间

    @Property(name = "medicalCode")
    private String medicalCode;         // 医学编码（如ICD-10）

    @Property(name = "category")
    private String category;            // 疾病分类（如轻度、中度、重度抑郁症）

    @Property(name = "synonyms")
    private Set<String> synonyms;       // 同义词

    @Property(name = "prevalenceRate")
    private Double prevalenceRate;      // 患病率

    @Property(name = "diagnosticCriteria")
    private String diagnosticCriteria;  // 诊断标准

    @Relationship(type = "HAS_SYMPTOM", direction = Relationship.Direction.OUTGOING)
    private Set<Symptom> symptoms;     // 相关症状

    @Relationship(type = "HAS_RISK_FACTOR", direction = Relationship.Direction.OUTGOING)
    private Set<Risk> riskFactors;     // 风险因素

    @Relationship(type = "CAN_BE_TREATED_BY", direction = Relationship.Direction.OUTGOING)
    private Set<Therapy> treatments;   // 治疗方法

    @Relationship(type = "CAN_BE_TREATED_WITH", direction = Relationship.Direction.OUTGOING)
    private Set<Medication> medications; // 相关药物
}

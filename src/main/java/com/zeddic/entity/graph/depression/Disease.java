package com.zeddic.entity.graph.depression;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * 疾病实体类，表示抑郁症及其分类
 *
 * @author Zeddic
 */
@Node("Disease")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Disease {
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 业务标识
     */
    @NotNull
    @Property(name = "entityId")
    private String entityId;

    /**
     * 实体定义
     */
    @Property(name = "definition")
    private String definition;

    /**
     * 创建时间
     */
    @Property(name = "createTime")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Property(name = "updateTime")
    private LocalDateTime updateTime;

    /**
     * 医学编码 ICD-11
     */
    @Property(name = "icd11Code")
    private String icd11Code;

    /**
     * 疾病分类（如轻度、中度、重度抑郁症）
     */
    @Property(name = "category")
    private String category;

    /**
     * 同义词
     */
    @Property(name = "synonyms")
    private Set<String> synonyms;

    /**
     * 诊断标准
     */
    @Property(name = "diagnosticCriteria")
    private String diagnosticCriteria;

    /**
     * 相关症状
     */
    @Relationship(type = "HAS_SYMPTOM", direction = Relationship.Direction.OUTGOING)
    private Set<Symptom> symptoms;

    /**
     * 风险因素
     */
    @Relationship(type = "HAS_RISK_FACTOR", direction = Relationship.Direction.OUTGOING)
    private Set<Risk> riskFactors;

    /**
     * 治疗方法
     */
    @Relationship(type = "CAN_BE_TREATED_BY", direction = Relationship.Direction.OUTGOING)
    private Set<Therapy> treatments;

    /**
     * 相关药物
     */
    @Relationship(type = "CAN_BE_TREATED_WITH", direction = Relationship.Direction.OUTGOING)
    private Set<Medication> medications;
}

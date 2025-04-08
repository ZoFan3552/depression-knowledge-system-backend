package com.zeddic.entity.graph.depression;

import com.alibaba.fastjson2.JSONObject;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 药物实体类，描述用于治疗抑郁症的药物
 *
 * @author Zeddic
 */
@Node
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Medication {
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
     * 通用名
     */
    @Property(name = "genericName")
    private String genericName;

    /**
     * 品牌名列表
     */
    @Property(name = "brandNames")
    private String brandNames;

    /**
     * 药物分类（如SSRI、SNRI等）
     */
    @Property(name = "drugClass")
    private String drugClass;

    /**
     * 作用机制
     */
    @Property(name = "mechanism")
    private String mechanism;

    /**
     * 剂量信息
     */
    @Property(name = "dosage")
    private String dosage;

    /**
     * 给药途径
     */
    @Property(name = "administrationRoute")
    private String administrationRoute;

    /**
     * 副作用列表
     */
    @Property(name = "sideEffects")
    private String sideEffects;

    /**
     * 禁忌症
     */
    @Property(name = "contraindications")
    private String contraindications;

    /**
     * 药物相互作用
     */
    @Property(name = "interactions")
    private String interactions;
}

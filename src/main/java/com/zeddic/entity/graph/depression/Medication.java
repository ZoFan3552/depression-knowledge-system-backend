package com.zeddic.entity.graph.depression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * 药物实体类，描述用于治疗抑郁症的药物
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

    @Property(name = "name")
    private String name;        // 实体名称

    @Property(name = "description")
    private String description; // 实体描述


    @Property(name = "createTime")
    private LocalDateTime createTime;    // 创建时间


    @Property(name = "updateTime")
    private LocalDateTime updateTime;    // 更新时间/

    @Property(name = "genericName")
    private String genericName;           // 通用名

    @Property(name = "brandNames")
    private List<String> brandNames;      // 品牌名列表

    @Property(name = "drugClass")
    private String drugClass;             // 药物分类（如SSRI、SNRI等）

    @Property(name = "mechanism")
    private String mechanism;             // 作用机制

    @Property(name = "dosage")
    private String dosage;                // 剂量信息

    @Property(name = "administrationRoute")
    private String administrationRoute;   // 给药途径

    @Property(name = "sideEffects")
    private Set<String> sideEffects;     // 副作用列表

    @Property(name = "contraindications")
    private Set<String> contraindications; // 禁忌症

    @Property(name = "interactions")
    private Set<String> interactions;    // 药物相互作用

    @Relationship(type = "TREATS", direction = Relationship.Direction.OUTGOING)
    private Set<Disease> treatedDiseases; // 治疗的疾病
}

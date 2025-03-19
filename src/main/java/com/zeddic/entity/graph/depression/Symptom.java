package com.zeddic.entity.graph.depression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * 症状实体类，描述抑郁症的各种症状
 */
@Node
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Symptom {
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

    @Property(name = "category")
    private String category;            // 症状分类（如情绪、认知、身体等）

    @Property(name = "severity")
    private Integer severity;           // 严重程度（1-10）

    @Property(name = "duration")
    private String duration;            // 持续时间描述

    @Property(name = "isCommon")
    private Boolean isCommon;           // 是否为常见症状

    @Property(name = "manifestations")
    private Set<String> manifestations;   // 具体表现形式

    @Relationship(type = "SYMPTOM_OF", direction = Relationship.Direction.OUTGOING)
    private Set<Disease> relatedDiseases; // 相关疾病
}

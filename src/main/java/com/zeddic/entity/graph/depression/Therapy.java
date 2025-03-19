package com.zeddic.entity.graph.depression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * 治疗方法实体类，描述抑郁症的各种治疗方式
 */
@Node
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Therapy {
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
    private String category;              // 治疗类别（如心理治疗、物理治疗等）

    @Property(name = "approach")
    private String approach;              // 具体方法（如认知行为疗法、电休克治疗等）

    @Property(name = "durationCourse")
    private String durationCourse;        // 治疗周期

    @Property(name = "sideEffects")
    private String sideEffects;           // 副作用

    @Relationship(type = "TREATS", direction = Relationship.Direction.OUTGOING)
    private Set<Disease> targetDiseases; // 目标疾病
}

package com.zeddic.entity.graph.depression;

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

/**
 * 治疗方法实体类，描述抑郁症的各种治疗方式
 * @author Zeddic
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

    /**
     * 实体名称
     */
    @Property(name = "name")
    @NotNull
    private String name;


    /**
     * 实体描述
     */
    @Property(name = "description")
    private String description;

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
     * 治疗类别（如心理治疗、物理治疗等）
     */
    @Property(name = "category")
    private String category;

    /**
     * 具体方法（如认知行为疗法、电休克治疗等）
     */
    @Property(name = "approach")
    private String approach;

    /**
     * 治疗周期
     */
    @Property(name = "durationCourse")
    private String durationCourse;

    /**
     * 副作用
     */
    @Property(name = "sideEffects")
    private String sideEffects;
}

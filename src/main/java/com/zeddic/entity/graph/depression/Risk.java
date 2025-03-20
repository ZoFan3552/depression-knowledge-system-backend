package com.zeddic.entity.graph.depression;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * 风险因素实体类，描述可能导致抑郁症的风险因素
 * @author Zeddic
 */
@Node
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Risk {
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
     * 风险类别（如生物学、心理学、社会环境等）
     */
    @Property(name = "category")
    private String category;

    /**
     * 影响因子（0-1）
     */
    @Property(name = "impactFactor")
    @Size(min = 0, max = 1)
    private Double impactFactor;

    /**
     * 证据级别（如A、B、C）
     */
    @Property(name = "evidenceLevel")
    private String evidenceLevel;

    /**
     * 预防措施
     */
    @Property(name = "preventiveMeasures")
    private Set<String> preventiveMeasures;
}

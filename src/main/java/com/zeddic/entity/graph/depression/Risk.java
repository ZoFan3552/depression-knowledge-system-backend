package com.zeddic.entity.graph.depression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * 风险因素实体类，描述可能导致抑郁症的风险因素
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

    @Property(name = "name")
    private String name;        // 实体名称

    @Property(name = "description")
    private String description; // 实体描述


    @Property(name = "createTime")
    private LocalDateTime createTime;    // 创建时间


    @Property(name = "updateTime")
    private LocalDateTime updateTime;    // 更新时间

    @Property(name = "category")
    private String category;              // 风险类别（如生物学、心理学、社会环境等）

    @Property(name = "impactFactor")
    private Double impactFactor;          // 影响因子（0-1）

    @Property(name = "evidenceLevel")
    private String evidenceLevel;         // 证据级别（如A、B、C）

    @Property(name = "preventiveMeasures")
    private Set<String> preventiveMeasures; // 预防措施

    @Relationship(type = "RISK_FOR", direction = Relationship.Direction.OUTGOING)
    private Set<Disease> relatedDiseases;   // 相关疾病
}

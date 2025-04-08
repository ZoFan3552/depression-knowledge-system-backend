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
 * 症状实体类，描述抑郁症的各种症状
 * @author Zeddic
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
     * 症状分类（如情绪、认知、身体等）
     */
    @Property(name = "category")
    private String category;

    /**
     * 严重程度
     */
    @Property(name = "severity")
    private String  severity;

    /**
     * 持续时间描述
     */
    @Property(name = "duration")
    private String duration;

    /**
     * 具体表现形式
     */
    @Property(name = "manifestations")
    private String manifestations;
}

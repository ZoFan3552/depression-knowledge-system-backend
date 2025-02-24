package com.zeddic.entity.knowledge;

import lombok.*;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * @author: zeddic
 * @description:
 * @date: 2025/1/19 下午5:27
 */
@Node("Cause")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cause {
    @Id
    private String name;
}

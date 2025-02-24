package com.zeddic.entity.knowledge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * @author: zeddic
 * @description:
 * @date: 2025/1/19 下午5:19
 */
@Node("Symptom")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Symptom {
    @Id
    private String name;
}

package com.zeddic.entity.knowledge;

import lombok.*;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;


@Node("Medication")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Medication {
    @Id
    private String name;
}

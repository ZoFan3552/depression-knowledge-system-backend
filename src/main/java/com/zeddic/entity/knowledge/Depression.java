package com.zeddic.entity.knowledge;

import lombok.*;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: zeddic
 * @description:
 * @date: 2025/1/19 下午5:18
 */
@Node("Depression")
@NoArgsConstructor
@Data
public class Depression {
    @Id
    @Getter
    private String name;

    public Depression(String name){
        this.name = name;
    }

    // 关系：一个Disease可能有多个Symptom
    @Relationship(type = "has_symptom", direction = Relationship.Direction.OUTGOING)
    private Set<Symptom> symptoms = new HashSet<>();

    // 关系：一个Disease可能有多个Cause
    @Relationship(type = "has_cause", direction = Relationship.Direction.OUTGOING)
    private Set<Cause> causes = new HashSet<>();

    // 关系：一个Disease可能由多个Diagnosis来诊断
    @Relationship(type = "diagnosed_by", direction = Relationship.Direction.OUTGOING)
    private Set<Diagnosis> diagnoses = new HashSet<>();

    // 关系：一个Disease可以通过多个Treatment治疗
    @Relationship(type = "treated_by", direction = Relationship.Direction.OUTGOING)
    private Set<Treatment> treatments = new HashSet<>();

    // 关系：一个Disease可以通过多个Prevention预防
    @Relationship(type = "prevented_by", direction = Relationship.Direction.OUTGOING)
    private Set<Prevention> preventions = new HashSet<>();

    // 关系：一个Disease可能有多个Medication
    @Relationship(type = "has_medication", direction = Relationship.Direction.OUTGOING)
    private Set<Medication> medications = new HashSet<>();
}

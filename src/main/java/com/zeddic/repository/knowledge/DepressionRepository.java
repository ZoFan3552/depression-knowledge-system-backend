package com.zeddic.repository.knowledge;

import com.zeddic.entity.knowledge.Depression;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

/**
 * @author: zeddic
 * @description:
 * @date: 2025/1/19 下午5:42
 */
public interface DepressionRepository extends Neo4jRepository<Depression, String> {
    //创建关系
    @Query("MERGE (d:Depression {name: $depressionName}) " +
            "MERGE (s:Symptom {name: $symptomName}) " +
            "MERGE (d)-[:has_symptom]->(s)")
    void addSymptomRelation(String depressionName, String symptomName);

    @Query("MERGE (d:Depression {name: $depressionName}) " +
            "MERGE (c:Cause {name: $causeName}) " +
            "MERGE (d)-[:has_cause]->(c)")
    void addCauseRelation(String depressionName, String causeName);

    @Query("MERGE (d:Depression {name: $depressionName}) " +
            "MERGE (diag:Diagnosis {name: $diagnosisName}) " +
            "MERGE (d)-[:diagnosed_by]->(diag)")
    void addDiagnosisRelation(String depressionName, String diagnosisName);

    @Query("MERGE (d:Depression {name: $depressionName}) " +
            "MERGE (t:Treatment {name: $treatmentName}) " +
            "MERGE (d)-[:treated_by]->(t)")
    void addTreatmentRelation(String depressionName, String treatmentName);

    @Query("MERGE (d:Depression {name: $depressionName}) " +
            "MERGE (p:Prevention {name: $preventionName}) " +
            "MERGE (d)-[:prevented_by]->(p)")
    void addPreventionRelation(String depressionName, String preventionName);

    @Query("MERGE (d:Depression {name: $depressionName}) " +
            "MERGE (m:Medication {name: $medicationName}) " +
            "MERGE (d)-[:has_medication]->(m)")
    void addMedicationRelation(String depressionName, String medicationName);

    //删除关系
    @Query("MATCH (d:Depression)-[r:has_symptom]->(s:Symptom) WHERE d.name = $depressionName AND s.name = $symptomName DELETE r")
    void deleteSymptomRelation(String depressionName, String symptomName);

    @Query("MATCH (d:Depression)-[r:has_cause]->(c:Cause) WHERE d.name = $depressionName AND c.name = $causeName DELETE r")
    void deleteCauseRelation(String depressionName, String causeName);

    @Query("MATCH (d:Depression)-[r:diagnosed_by]->(diag:Diagnosis) WHERE d.name = $depressionName AND diag.name = $diagnosisName DELETE r")
    void deleteDiagnosisRelation(String depressionName, String diagnosisName);

    @Query("MATCH (d:Depression)-[r:treated_by]->(t:Treatment) WHERE d.name = $depressionName AND t.name = $treatmentName DELETE r")
    void deleteTreatmentRelation(String depressionName, String treatmentName);

    @Query("MATCH (d:Depression)-[r:prevented_by]->(p:Prevention) WHERE d.name = $depressionName AND p.name = $preventionName DELETE r")
    void deletePreventionRelation(String depressionName, String preventionName);

    @Query("MATCH (d:Depression)-[r:has_medication]->(m:Medication) WHERE d.name = $depressionName AND m.name = $medicationName DELETE r")
    void deleteMedicationRelation(String depressionName, String medicationName);
}

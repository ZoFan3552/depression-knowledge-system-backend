package com.zeddic.repository.graph;

import com.zeddic.entity.graph.depression.Medication;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Zeddic
 */
@Repository
public interface MedicationRepository extends Neo4jRepository<Medication, Long> {

}
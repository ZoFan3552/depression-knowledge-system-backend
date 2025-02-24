package com.zeddic.repository.knowledge;

import com.zeddic.entity.knowledge.Medication;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

/**
 * @author: zeddic
 * @description:
 * @date: 2025/1/19 下午6:17
 */
public interface MedicationRepository extends Neo4jRepository<Medication, String> {
}

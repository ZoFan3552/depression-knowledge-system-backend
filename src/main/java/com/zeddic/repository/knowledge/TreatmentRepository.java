package com.zeddic.repository.knowledge;

import com.zeddic.entity.knowledge.Treatment;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

/**
 * @author: zeddic
 * @description:
 * @date: 2025/1/19 下午6:19
 */
public interface TreatmentRepository extends Neo4jRepository<Treatment, String> {
}

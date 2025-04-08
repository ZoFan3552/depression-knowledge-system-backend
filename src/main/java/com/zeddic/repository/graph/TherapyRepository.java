package com.zeddic.repository.graph;

import com.zeddic.entity.graph.depression.Therapy;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Zeddic
 */
@Repository
public interface TherapyRepository extends Neo4jRepository<Therapy, Long> {

}
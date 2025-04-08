package com.zeddic.repository.graph;

import com.zeddic.entity.graph.depression.Risk;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Zeddic
 */
@Repository
public interface RiskRepository extends Neo4jRepository<Risk, Long> {

}
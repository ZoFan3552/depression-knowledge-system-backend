package com.zeddic.repository.graph;

import com.zeddic.entity.graph.depression.Disease;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Zeddic
 */
@Repository
public interface DiseaseRepository extends Neo4jRepository<Disease, Long> {

}

package com.zeddic.repository.knowledge;

import com.zeddic.entity.knowledge.Cause;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

/**
 * @author: zeddic
 * @description:
 * @date: 2025/1/19 下午6:16
 */
public interface CauseRepository extends Neo4jRepository<Cause, String> {
    @Query("MATCH (c:Cause) WHERE c.name = $causeName DETACH DELETE c")
    void deleteCauseNodeAndRelationByName(String causeName);
}


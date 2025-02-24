package com.zeddic.repository.knowledge;

import com.zeddic.entity.knowledge.Prevention;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

/**
 * @author: zeddic
 * @description:
 * @date: 2025/1/19 下午6:18
 */
public interface PreventionRepository extends Neo4jRepository<Prevention, String> {

}

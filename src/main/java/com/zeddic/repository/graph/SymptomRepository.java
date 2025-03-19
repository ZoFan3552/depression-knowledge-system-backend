package com.zeddic.repository.graph;

import com.zeddic.entity.graph.depression.Symptom;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SymptomRepository extends Neo4jRepository<Symptom, Long> {

}

package com.zeddic.repository.graph;

import com.zeddic.entity.graph.depression.Therapy;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Zeddic
 */
@Repository
public interface TherapyRepository extends Neo4jRepository<Therapy, Long> {

    /**
     * 根据治疗方法名称查询
     * @param name 治疗方法名称
     * @return 治疗方法信息
     */
    Optional<Therapy> findByName(String name);

    /**
     * 根据治疗类别查询
     * @param category 治疗类别
     * @return 治疗方法列表
     */
    List<Therapy> findByCategory(String category);

    /**
     * 根据具体方法查询
     * @param approach 具体方法
     * @return 治疗方法列表
     */
    List<Therapy> findByApproach(String approach);

    /**
     * 根据治疗周期查询
     * @param durationCourse 治疗周期
     * @return 治疗方法列表
     */
    List<Therapy> findByDurationCourse(String durationCourse);

    /**
     * 根据副作用模糊查询
     * @param keyword 副作用关键词
     * @return 治疗方法列表
     */
    @Query("MATCH (t:Therapy) WHERE t.sideEffects CONTAINS $keyword RETURN t")
    List<Therapy> findBySideEffectsContaining(@Param("keyword") String keyword);

    /**
     * 查询与特定疾病相关的治疗方法
     * @param diseaseName 疾病名称
     * @return 治疗方法列表
     */
    @Query("MATCH (d:Disease)-[:CAN_BE_TREATED_BY]->(t:Therapy) WHERE d.name = $diseaseName RETURN t")
    List<Therapy> findByRelatedDiseaseName(@Param("diseaseName") String diseaseName);

    /**
     * 根据治疗类别和具体方法查询
     * @param category 治疗类别
     * @param approach 具体方法
     * @return 治疗方法列表
     */
    List<Therapy> findByCategoryAndApproach(String category, String approach);

    /**
     * 根据描述关键词模糊查询
     * @param keyword 关键词
     * @return 治疗方法列表
     */
    @Query("MATCH (t:Therapy) WHERE t.description CONTAINS $keyword RETURN t")
    List<Therapy> findByDescriptionContaining(@Param("keyword") String keyword);

    /**
     * 分页查询所有治疗方法
     * @param skip 跳过数量
     * @param limit 限制数量
     * @return 治疗方法列表
     */
    @Query("MATCH (t:Therapy) RETURN t SKIP $skip LIMIT $limit")
    List<Therapy> findAllWithPagination(@Param("skip") int skip, @Param("limit") int limit);

    /**
     * 根据疾病ID查询相关的治疗方法
     * @param diseaseId 疾病ID
     * @return 治疗方法列表
     */
    @Query("MATCH (d:Disease)-[:CAN_BE_TREATED_BY]->(t:Therapy) WHERE ID(d) = $diseaseId RETURN t")
    List<Therapy> findByDiseaseId(@Param("diseaseId") Long diseaseId);
}
package com.zeddic.repository.graph;

import com.zeddic.entity.graph.depression.Symptom;
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
public interface SymptomRepository extends Neo4jRepository<Symptom, Long> {

    /**
     * 根据症状名称查询
     * @param name 症状名称
     * @return 症状信息
     */
    Optional<Symptom> findByName(String name);

    /**
     * 根据症状分类查询
     * @param category 症状分类
     * @return 症状列表
     */
    List<Symptom> findByCategory(String category);

    /**
     * 根据严重程度范围查询
     * @param minSeverity 最小严重程度
     * @param maxSeverity 最大严重程度
     * @return 症状列表
     */
    @Query("MATCH (s:Symptom) WHERE s.severity >= $minSeverity AND s.severity <= $maxSeverity RETURN s ORDER BY s.severity DESC")
    List<Symptom> findBySeverityBetween(@Param("minSeverity") Integer minSeverity, @Param("maxSeverity") Integer maxSeverity);

    /**
     * 查询常见症状
     * @return 症状列表
     */
    List<Symptom> findByIsCommonTrue();

    /**
     * 查询非常见症状
     * @return 症状列表
     */
    List<Symptom> findByIsCommonFalse();

    /**
     * 查询具有特定表现形式的症状
     * @param manifestation 表现形式
     * @return 症状列表
     */
    @Query("MATCH (s:Symptom) WHERE $manifestation IN s.manifestations RETURN s")
    List<Symptom> findByManifestation(@Param("manifestation") String manifestation);

    /**
     * 查询与特定疾病相关的症状
     * @param diseaseName 疾病名称
     * @return 症状列表
     */
    @Query("MATCH (d:Disease)-[:HAS_SYMPTOM]->(s:Symptom) WHERE d.name = $diseaseName RETURN s")
    List<Symptom> findByRelatedDiseaseName(@Param("diseaseName") String diseaseName);

    /**
     * 根据严重程度降序排列查询所有症状
     * @return 症状列表
     */
    @Query("MATCH (s:Symptom) RETURN s ORDER BY s.severity DESC")
    List<Symptom> findAllOrderBySeverityDesc();

    /**
     * 分页查询所有症状
     * @param skip 跳过数量
     * @param limit 限制数量
     * @return 症状列表
     */
    @Query("MATCH (s:Symptom) RETURN s SKIP $skip LIMIT $limit")
    List<Symptom> findAllWithPagination(@Param("skip") int skip, @Param("limit") int limit);

    /**
     * 根据描述关键词模糊查询
     * @param keyword 关键词
     * @return 症状列表
     */
    @Query("MATCH (s:Symptom) WHERE s.description CONTAINS $keyword RETURN s")
    List<Symptom> findByDescriptionContaining(@Param("keyword") String keyword);

    /**
     * 根据持续时间查询
     * @param duration 持续时间
     * @return 症状列表
     */
    List<Symptom> findByDuration(String duration);

    /**
     * 根据分类和是否常见查询
     * @param category 分类
     * @param isCommon 是否常见
     * @return 症状列表
     */
    List<Symptom> findByCategoryAndIsCommon(String category, Boolean isCommon);
}
package com.zeddic.repository.graph;

import com.zeddic.entity.graph.depression.Risk;
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
public interface RiskRepository extends Neo4jRepository<Risk, Long> {

    /**
     * 根据风险因素名称查询
     *
     * @param name 风险因素名称
     * @return 风险因素信息
     */
    Optional<Risk> findByName(String name);

    /**
     * 根据风险类别查询
     *
     * @param category 风险类别
     * @return 风险因素列表
     */
    List<Risk> findByCategory(String category);

    /**
     * 根据证据级别查询
     *
     * @param evidenceLevel 证据级别
     * @return 风险因素列表
     */
    List<Risk> findByEvidenceLevel(String evidenceLevel);

    /**
     * 根据影响因子范围查询
     *
     * @param minFactor 最小影响因子
     * @param maxFactor 最大影响因子
     * @return 风险因素列表
     */
    @Query("MATCH (r:Risk) WHERE r.impactFactor >= $minFactor AND r.impactFactor <= $maxFactor RETURN r ORDER BY r.impactFactor DESC")
    List<Risk> findByImpactFactorBetween(@Param("minFactor") Double minFactor, @Param("maxFactor") Double maxFactor);

    /**
     * 查询具有特定预防措施的风险因素
     *
     * @param measure 预防措施
     * @return 风险因素列表
     */
    @Query("MATCH (r:Risk) WHERE $measure IN r.preventiveMeasures RETURN r")
    List<Risk> findByPreventiveMeasure(@Param("measure") String measure);

    /**
     * 查询与特定疾病相关的风险因素
     *
     * @param diseaseName 疾病名称
     * @return 风险因素列表
     */
    @Query("MATCH (d:Disease)-[:HAS_RISK_FACTOR]->(r:Risk) WHERE d.name = $diseaseName RETURN r")
    List<Risk> findByRelatedDiseaseName(@Param("diseaseName") String diseaseName);

    /**
     * 根据影响因子降序排列查询所有风险因素
     *
     * @return 风险因素列表
     */
    @Query("MATCH (r:Risk) RETURN r ORDER BY r.impactFactor DESC")
    List<Risk> findAllOrderByImpactFactorDesc();

    /**
     * 分页查询所有风险因素
     *
     * @param skip  跳过数量
     * @param limit 限制数量
     * @return 风险因素列表
     */
    @Query("MATCH (r:Risk) RETURN r SKIP $skip LIMIT $limit")
    List<Risk> findAllWithPagination(@Param("skip") int skip, @Param("limit") int limit);

    /**
     * 根据描述关键词模糊查询
     *
     * @param keyword 关键词
     * @return 风险因素列表
     */
    @Query("MATCH (r:Risk) WHERE r.description CONTAINS $keyword RETURN r")
    List<Risk> findByDescriptionContaining(@Param("keyword") String keyword);
}
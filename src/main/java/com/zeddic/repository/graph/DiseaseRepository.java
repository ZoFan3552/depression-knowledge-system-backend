package com.zeddic.repository.graph;

import com.zeddic.entity.graph.depression.Disease;
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
public interface DiseaseRepository extends Neo4jRepository<Disease, Long> {
    /**
     * 根据疾病名称查询
     *
     * @param name 疾病名称
     * @return 疾病信息
     */
    Optional<Disease> findByName(String name);

    /**
     * 根据疾病分类查询列表
     *
     * @param category 疾病分类
     * @return 疾病列表
     */
    List<Disease> findByCategory(String category);

    /**
     * 根据医学编码查询
     *
     * @param medicalCode 医学编码
     * @return 疾病信息
     */
    Optional<Disease> findByMedicalCode(String medicalCode);

    /**
     * 查询包含特定症状的疾病
     *
     * @param symptomName 症状名称
     * @return 疾病列表
     */
    @Query("MATCH (d:Disease)-[:HAS_SYMPTOM]->(s:Symptom) WHERE s.name = $symptomName RETURN d")
    List<Disease> findBySymptomName(@Param("symptomName") String symptomName);

    /**
     * 查询可被特定治疗方法治疗的疾病
     *
     * @param therapyName 治疗方法名称
     * @return 疾病列表
     */
    @Query("MATCH (d:Disease)-[:CAN_BE_TREATED_BY]->(t:Therapy) WHERE t.name = $therapyName RETURN d")
    List<Disease> findByTherapyName(@Param("therapyName") String therapyName);

    /**
     * 查询可被特定药物治疗的疾病
     *
     * @param medicationName 药物名称
     * @return 疾病列表
     */
    @Query("MATCH (d:Disease)-[:CAN_BE_TREATED_WITH]->(m:Medication) WHERE m.name = $medicationName RETURN d")
    List<Disease> findByMedicationName(@Param("medicationName") String medicationName);

    /**
     * 查询具有特定风险因素的疾病
     *
     * @param riskName 风险因素名称
     * @return 疾病列表
     */
    @Query("MATCH (d:Disease)-[:HAS_RISK_FACTOR]->(r:Risk) WHERE r.name = $riskName RETURN d")
    List<Disease> findByRiskFactorName(@Param("riskName") String riskName);

    /**
     * 根据同义词查询疾病
     *
     * @param synonym 同义词
     * @return 疾病列表
     */
    @Query("MATCH (d:Disease) WHERE $synonym IN d.synonyms RETURN d")
    List<Disease> findBySynonym(@Param("synonym") String synonym);

    /**
     * 根据患病率范围查询疾病
     *
     * @param minRate 最小患病率
     * @param maxRate 最大患病率
     * @return 疾病列表
     */
    @Query("MATCH (d:Disease) WHERE d.prevalenceRate >= $minRate AND d.prevalenceRate <= $maxRate RETURN d")
    List<Disease> findByPrevalenceRateBetween(@Param("minRate") Double minRate, @Param("maxRate") Double maxRate);
}

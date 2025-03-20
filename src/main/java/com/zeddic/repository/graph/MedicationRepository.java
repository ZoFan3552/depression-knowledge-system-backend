package com.zeddic.repository.graph;

import com.zeddic.entity.graph.depression.Medication;
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
public interface MedicationRepository extends Neo4jRepository<Medication, Long> {

    /**
     * 根据药物名称查询
     *
     * @param name 药物名称
     * @return 药物信息
     */
    Optional<Medication> findByName(String name);

    /**
     * 根据药物通用名查询
     *
     * @param genericName 药物通用名
     * @return 药物信息
     */
    Optional<Medication> findByGenericName(String genericName);

    /**
     * 根据品牌名查询药物
     *
     * @param brandName 品牌名
     * @return 药物列表
     */
    @Query("MATCH (m:Medication) WHERE $brandName IN m.brandNames RETURN m")
    List<Medication> findByBrandName(@Param("brandName") String brandName);

    /**
     * 根据药物分类查询
     *
     * @param drugClass 药物分类
     * @return 药物列表
     */
    List<Medication> findByDrugClass(String drugClass);

    /**
     * 根据给药途径查询
     *
     * @param route 给药途径
     * @return 药物列表
     */
    List<Medication> findByAdministrationRoute(String route);

    /**
     * 查询包含特定副作用的药物
     *
     * @param sideEffect 副作用
     * @return 药物列表
     */
    @Query("MATCH (m:Medication) WHERE $sideEffect IN m.sideEffects RETURN m")
    List<Medication> findBySideEffect(@Param("sideEffect") String sideEffect);

    /**
     * 查询包含特定禁忌症的药物
     *
     * @param contraindication 禁忌症
     * @return 药物列表
     */
    @Query("MATCH (m:Medication) WHERE $contraindication IN m.contraindications RETURN m")
    List<Medication> findByContraindication(@Param("contraindication") String contraindication);

    /**
     * 查询包含特定药物相互作用的药物
     *
     * @param interaction 药物相互作用
     * @return 药物列表
     */
    @Query("MATCH (m:Medication) WHERE $interaction IN m.interactions RETURN m")
    List<Medication> findByInteraction(@Param("interaction") String interaction);

    /**
     * 根据作用机制模糊查询药物
     *
     * @param mechanismKeyword 作用机制关键词
     * @return 药物列表
     */
    @Query("MATCH (m:Medication) WHERE m.mechanism CONTAINS $mechanismKeyword RETURN m")
    List<Medication> findByMechanismContaining(@Param("mechanismKeyword") String mechanismKeyword);
}
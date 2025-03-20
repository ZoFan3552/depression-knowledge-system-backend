package com.zeddic.service.graph.depression;

import com.zeddic.entity.graph.depression.Risk;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author Zeddic
 */
public interface RiskService {

    /**
     * 保存风险因素信息
     *
     * @param risk 风险因素实体
     * @return 保存后的风险因素实体
     */
    Risk saveRisk(Risk risk);

    /**
     * 根据ID查询风险因素
     *
     * @param id 风险因素ID
     * @return 风险因素信息
     */
    Optional<Risk> findRiskById(Long id);

    /**
     * 根据名称查询风险因素
     *
     * @param name 风险因素名称
     * @return 风险因素信息
     */
    Optional<Risk> findRiskByName(String name);

    /**
     * 查询所有风险因素
     *
     * @return 风险因素列表
     */
    List<Risk> findAllRisks();

    /**
     * 根据风险类别查询
     *
     * @param category 风险类别
     * @return 风险因素列表
     */
    List<Risk> findRisksByCategory(String category);

    /**
     * 根据证据级别查询
     *
     * @param evidenceLevel 证据级别
     * @return 风险因素列表
     */
    List<Risk> findRisksByEvidenceLevel(String evidenceLevel);

    /**
     * 根据影响因子范围查询
     *
     * @param minFactor 最小影响因子
     * @param maxFactor 最大影响因子
     * @return 风险因素列表
     */
    List<Risk> findRisksByImpactFactorBetween(Double minFactor, Double maxFactor);

    /**
     * 查询具有特定预防措施的风险因素
     *
     * @param measure 预防措施
     * @return 风险因素列表
     */
    List<Risk> findRisksByPreventiveMeasure(String measure);

    /**
     * 查询与特定疾病相关的风险因素
     *
     * @param diseaseName 疾病名称
     * @return 风险因素列表
     */
    List<Risk> findRisksByRelatedDiseaseName(String diseaseName);

    /**
     * 根据影响因子降序排列查询所有风险因素
     *
     * @return 风险因素列表
     */
    List<Risk> findAllRisksOrderByImpactFactorDesc();

    /**
     * 根据描述关键词模糊查询
     *
     * @param keyword 关键词
     * @return 风险因素列表
     */
    List<Risk> findRisksByDescriptionContaining(String keyword);

    /**
     * 更新风险因素信息
     *
     * @param risk 风险因素实体
     * @return 更新后的风险因素实体
     */
    Risk updateRisk(Risk risk);

    /**
     * 添加预防措施到风险因素
     *
     * @param riskId             风险因素ID
     * @param preventiveMeasures 预防措施集合
     * @return 更新后的风险因素实体
     */
    Risk addPreventiveMeasuresToRisk(Long riskId, Set<String> preventiveMeasures);

    /**
     * 删除风险因素
     *
     * @param id 风险因素ID
     */
    void deleteRisk(Long id);

    /**
     * 删除风险因素预防措施
     *
     * @param riskId  风险因素ID
     * @param measure 预防措施
     * @return 更新后的风险因素实体
     */
    Risk removePreventiveMeasureFromRisk(Long riskId, String measure);

    /**
     * 批量保存风险因素
     *
     * @param risks 风险因素列表
     * @return 保存后的风险因素列表
     */
    List<Risk> saveAllRisks(List<Risk> risks);

    /**
     * 统计不同类别的风险因素数量
     *
     * @return 类别-数量映射
     */
    Map<String, Long> countRisksByCategory();

    /**
     * 获取影响因子最高的前N个风险因素
     *
     * @param n 数量
     * @return 风险因素列表
     */
    List<Risk> findTopNthRisksByImpactFactor(int n);
}
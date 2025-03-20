package com.zeddic.service.graph.depression;

import com.zeddic.entity.graph.depression.Symptom;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author Zeddic
 */
public interface SymptomService {

    /**
     * 保存症状信息
     * @param symptom 症状实体
     * @return 保存后的症状实体
     */
    Symptom saveSymptom(Symptom symptom);

    /**
     * 根据ID查询症状
     * @param id 症状ID
     * @return 症状信息
     */
    Optional<Symptom> findSymptomById(Long id);

    /**
     * 根据名称查询症状
     * @param name 症状名称
     * @return 症状信息
     */
    Optional<Symptom> findSymptomByName(String name);

    /**
     * 查询所有症状
     * @return 症状列表
     */
    List<Symptom> findAllSymptoms();

    /**
     * 根据症状分类查询
     * @param category 症状分类
     * @return 症状列表
     */
    List<Symptom> findSymptomsByCategory(String category);

    /**
     * 根据严重程度范围查询
     * @param minSeverity 最小严重程度
     * @param maxSeverity 最大严重程度
     * @return 症状列表
     */
    List<Symptom> findSymptomsBySeverityBetween(Integer minSeverity, Integer maxSeverity);

    /**
     * 查询常见症状
     * @return 症状列表
     */
    List<Symptom> findCommonSymptoms();

    /**
     * 查询非常见症状
     * @return 症状列表
     */
    List<Symptom> findUncommonSymptoms();

    /**
     * 查询具有特定表现形式的症状
     * @param manifestation 表现形式
     * @return 症状列表
     */
    List<Symptom> findSymptomsByManifestation(String manifestation);

    /**
     * 查询与特定疾病相关的症状
     * @param diseaseName 疾病名称
     * @return 症状列表
     */
    List<Symptom> findSymptomsByRelatedDiseaseName(String diseaseName);

    /**
     * 根据严重程度降序排列查询所有症状
     * @return 症状列表
     */
    List<Symptom> findAllSymptomsOrderBySeverityDesc();

    /**
     * 根据描述关键词模糊查询
     * @param keyword 关键词
     * @return 症状列表
     */
    List<Symptom> findSymptomsByDescriptionContaining(String keyword);

    /**
     * 根据持续时间查询
     * @param duration 持续时间
     * @return 症状列表
     */
    List<Symptom> findSymptomsByDuration(String duration);

    /**
     * 根据分类和是否常见查询
     * @param category 分类
     * @param isCommon 是否常见
     * @return 症状列表
     */
    List<Symptom> findSymptomsByCategoryAndIsCommon(String category, Boolean isCommon);

    /**
     * 更新症状信息
     * @param symptom 症状实体
     * @return 更新后的症状实体
     */
    Symptom updateSymptom(Symptom symptom);

    /**
     * 添加表现形式到症状
     * @param symptomId 症状ID
     * @param manifestations 表现形式集合
     * @return 更新后的症状实体
     */
    Symptom addManifestationsToSymptom(Long symptomId, Set<String> manifestations);

    /**
     * 删除症状
     * @param id 症状ID
     */
    void deleteSymptom(Long id);

    /**
     * 删除症状表现形式
     * @param symptomId 症状ID
     * @param manifestation 表现形式
     * @return 更新后的症状实体
     */
    Symptom removeManifestationFromSymptom(Long symptomId, String manifestation);

    /**
     * 批量保存症状
     * @param symptoms 症状列表
     * @return 保存后的症状列表
     */
    List<Symptom> saveAllSymptoms(List<Symptom> symptoms);

    /**
     * 统计不同分类的症状数量
     * @return 分类-数量映射
     */
    Map<String, Long> countSymptomsByCategory();

    /**
     * 获取严重程度最高的前N个症状
     * @param n 数量
     * @return 症状列表
     */
    List<Symptom> findTopNthSymptomsBySeverity(int n);

    /**
     * 根据分类获取严重程度最高的症状
     * @param category 分类
     * @return 症状信息
     */
    Optional<Symptom> findMostSevereSymptomByCategory(String category);

    /**
     * 检查症状是否存在
     * @param id 症状ID
     * @return 是否存在
     */
    boolean existsSymptomById(Long id);
}
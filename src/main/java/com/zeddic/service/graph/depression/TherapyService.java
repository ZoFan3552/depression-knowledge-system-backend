package com.zeddic.service.graph.depression;

import com.zeddic.entity.graph.depression.Therapy;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Zeddic
 */
public interface TherapyService {

    /**
     * 保存治疗方法信息
     * @param therapy 治疗方法实体
     * @return 保存后的治疗方法实体
     */
    Therapy saveTherapy(Therapy therapy);

    /**
     * 根据ID查询治疗方法
     * @param id 治疗方法ID
     * @return 治疗方法信息
     */
    Optional<Therapy> findTherapyById(Long id);

    /**
     * 根据名称查询治疗方法
     * @param name 治疗方法名称
     * @return 治疗方法信息
     */
    Optional<Therapy> findTherapyByName(String name);

    /**
     * 查询所有治疗方法
     * @return 治疗方法列表
     */
    List<Therapy> findAllTherapies();

    /**
     * 根据治疗类别查询
     * @param category 治疗类别
     * @return 治疗方法列表
     */
    List<Therapy> findTherapiesByCategory(String category);

    /**
     * 根据具体方法查询
     * @param approach 具体方法
     * @return 治疗方法列表
     */
    List<Therapy> findTherapiesByApproach(String approach);

    /**
     * 根据治疗周期查询
     * @param durationCourse 治疗周期
     * @return 治疗方法列表
     */
    List<Therapy> findTherapiesByDurationCourse(String durationCourse);

    /**
     * 根据副作用关键词查询
     * @param keyword 副作用关键词
     * @return 治疗方法列表
     */
    List<Therapy> findTherapiesBySideEffectsContaining(String keyword);

    /**
     * 查询与特定疾病相关的治疗方法
     * @param diseaseName 疾病名称
     * @return 治疗方法列表
     */
    List<Therapy> findTherapiesByRelatedDiseaseName(String diseaseName);

    /**
     * 根据治疗类别和具体方法查询
     * @param category 治疗类别
     * @param approach 具体方法
     * @return 治疗方法列表
     */
    List<Therapy> findTherapiesByCategoryAndApproach(String category, String approach);

    /**
     * 根据描述关键词模糊查询
     * @param keyword 关键词
     * @return 治疗方法列表
     */
    List<Therapy> findTherapiesByDescriptionContaining(String keyword);

    /**
     * 根据疾病ID查询相关的治疗方法
     * @param diseaseId 疾病ID
     * @return 治疗方法列表
     */
    List<Therapy> findTherapiesByDiseaseId(Long diseaseId);

    /**
     * 更新治疗方法信息
     * @param therapy 治疗方法实体
     * @return 更新后的治疗方法实体
     */
    Therapy updateTherapy(Therapy therapy);

    /**
     * 删除治疗方法
     * @param id 治疗方法ID
     */
    void deleteTherapy(Long id);

    /**
     * 批量保存治疗方法
     * @param therapies 治疗方法列表
     * @return 保存后的治疗方法列表
     */
    List<Therapy> saveAllTherapies(List<Therapy> therapies);

    /**
     * 统计不同类别的治疗方法数量
     * @return 类别-数量映射
     */
    Map<String, Long> countTherapiesByCategory();

    /**
     * 检查治疗方法是否存在
     * @param id 治疗方法ID
     * @return 是否存在
     */
    boolean existsTherapyById(Long id);

    /**
     * 获取所有治疗类别
     * @return 类别列表
     */
    List<String> findAllTherapyCategories();

    /**
     * 获取所有具体方法
     * @return 具体方法列表
     */
    List<String> findAllTherapyApproaches();
}
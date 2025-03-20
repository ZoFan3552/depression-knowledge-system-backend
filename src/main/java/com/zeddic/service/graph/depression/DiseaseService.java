package com.zeddic.service.graph.depression;

import com.zeddic.entity.graph.depression.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Zeddic
 */
public interface DiseaseService {
    /**
     * 保存疾病信息
     * @param disease 疾病实体
     * @return 保存后的疾病实体
     */
    Disease saveDisease(Disease disease);

    /**
     * 根据ID查询疾病
     * @param id 疾病ID
     * @return 疾病信息
     */
    Optional<Disease> findDiseaseById(Long id);

    /**
     * 根据名称查询疾病
     * @param name 疾病名称
     * @return 疾病信息
     */
    Optional<Disease> findDiseaseByName(String name);

    /**
     * 根据医学编码查询疾病
     * @param medicalCode 医学编码
     * @return 疾病信息
     */
    Optional<Disease> findDiseaseByMedicalCode(String medicalCode);

    /**
     * 查询所有疾病
     * @return 疾病列表
     */
    List<Disease> findAllDiseases();

    /**
     * 根据分类查询疾病
     * @param category 疾病分类
     * @return 疾病列表
     */
    List<Disease> findDiseasesByCategory(String category);

    /**
     * 查询具有特定症状的疾病
     * @param symptomName 症状名称
     * @return 疾病列表
     */
    List<Disease> findDiseasesBySymptomName(String symptomName);

    /**
     * 查询可被特定治疗方法治疗的疾病
     * @param therapyName 治疗方法名称
     * @return 疾病列表
     */
    List<Disease> findDiseasesByTherapyName(String therapyName);

    /**
     * 查询可被特定药物治疗的疾病
     * @param medicationName 药物名称
     * @return 疾病列表
     */
    List<Disease> findDiseasesByMedicationName(String medicationName);

    /**
     * 查询具有特定风险因素的疾病
     * @param riskName 风险因素名称
     * @return 疾病列表
     */
    List<Disease> findDiseasesByRiskFactorName(String riskName);

    /**
     * 根据同义词查询疾病
     * @param synonym 同义词
     * @return 疾病列表
     */
    List<Disease> findDiseasesBySynonym(String synonym);

    /**
     * 根据患病率范围查询疾病
     * @param minRate 最小患病率
     * @param maxRate 最大患病率
     * @return 疾病列表
     */
    List<Disease> findDiseasesByPrevalenceRateBetween(Double minRate, Double maxRate);

    /**
     * 更新疾病信息
     * @param disease 疾病实体
     * @return 更新后的疾病实体
     */
    Disease updateDisease(Disease disease);

    /**
     * 添加疾病症状关系
     * @param diseaseId 疾病ID
     * @param symptoms 症状集合
     * @return 更新后的疾病实体
     */
    Disease addSymptomsToDisease(Long diseaseId, Set<Symptom> symptoms);

    /**
     * 添加疾病治疗方法关系
     * @param diseaseId 疾病ID
     * @param therapies 治疗方法集合
     * @return 更新后的疾病实体
     */
    Disease addTherapiesToDisease(Long diseaseId, Set<Therapy> therapies);

    /**
     * 添加疾病药物关系
     * @param diseaseId 疾病ID
     * @param medications 药物集合
     * @return 更新后的疾病实体
     */
    Disease addMedicationsToDisease(Long diseaseId, Set<Medication> medications);

    /**
     * 添加疾病风险因素关系
     * @param diseaseId 疾病ID
     * @param riskFactors 风险因素集合
     * @return 更新后的疾病实体
     */
    Disease addRiskFactorsToDisease(Long diseaseId, Set<Risk> riskFactors);

    /**
     * 添加疾病同义词
     * @param diseaseId 疾病ID
     * @param synonyms 同义词集合
     * @return 更新后的疾病实体
     */
    Disease addSynonymsToDisease(Long diseaseId, Set<String> synonyms);

    /**
     * 删除疾病
     * @param id 疾病ID
     */
    void deleteDisease(Long id);

    /**
     * 删除疾病症状关系
     * @param diseaseId 疾病ID
     * @param symptomId 症状ID
     * @return 更新后的疾病实体
     */
    Disease removeSymptomFromDisease(Long diseaseId, Long symptomId);

    /**
     * 删除疾病治疗方法关系
     * @param diseaseId 疾病ID
     * @param therapyId 治疗方法ID
     * @return 更新后的疾病实体
     */
    Disease removeTherapyFromDisease(Long diseaseId, Long therapyId);

    /**
     * 删除疾病药物关系
     * @param diseaseId 疾病ID
     * @param medicationId 药物ID
     * @return 更新后的疾病实体
     */
    Disease removeMedicationFromDisease(Long diseaseId, Long medicationId);

    /**
     * 删除疾病风险因素关系
     * @param diseaseId 疾病ID
     * @param riskId 风险因素ID
     * @return 更新后的疾病实体
     */
    Disease removeRiskFactorFromDisease(Long diseaseId, Long riskId);

    /**
     * 删除疾病同义词
     * @param diseaseId 疾病ID
     * @param synonym 同义词
     * @return 更新后的疾病实体
     */
    Disease removeSynonymFromDisease(Long diseaseId, String synonym);
}

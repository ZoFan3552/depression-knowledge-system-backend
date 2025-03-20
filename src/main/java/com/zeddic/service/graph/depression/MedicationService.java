package com.zeddic.service.graph.depression;

import com.zeddic.entity.graph.depression.Medication;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Zeddic
 */
public interface MedicationService {

    /**
     * 保存药物信息
     *
     * @param medication 药物实体
     * @return 保存后的药物实体
     */
    Medication saveMedication(Medication medication);

    /**
     * 根据ID查询药物
     *
     * @param id 药物ID
     * @return 药物信息
     */
    Optional<Medication> findMedicationById(Long id);

    /**
     * 根据名称查询药物
     *
     * @param name 药物名称
     * @return 药物信息
     */
    Optional<Medication> findMedicationByName(String name);

    /**
     * 根据通用名查询药物
     *
     * @param genericName 药物通用名
     * @return 药物信息
     */
    Optional<Medication> findMedicationByGenericName(String genericName);

    /**
     * 根据品牌名查询药物
     *
     * @param brandName 品牌名
     * @return 药物列表
     */
    List<Medication> findMedicationsByBrandName(String brandName);

    /**
     * 查询所有药物
     *
     * @return 药物列表
     */
    List<Medication> findAllMedications();

    /**
     * 根据药物分类查询
     *
     * @param drugClass 药物分类
     * @return 药物列表
     */
    List<Medication> findMedicationsByDrugClass(String drugClass);

    /**
     * 根据给药途径查询
     *
     * @param route 给药途径
     * @return 药物列表
     */
    List<Medication> findMedicationsByAdministrationRoute(String route);

    /**
     * 查询包含特定副作用的药物
     *
     * @param sideEffect 副作用
     * @return 药物列表
     */
    List<Medication> findMedicationsBySideEffect(String sideEffect);

    /**
     * 查询包含特定禁忌症的药物
     *
     * @param contraindication 禁忌症
     * @return 药物列表
     */
    List<Medication> findMedicationsByContraindication(String contraindication);

    /**
     * 查询包含特定药物相互作用的药物
     *
     * @param interaction 药物相互作用
     * @return 药物列表
     */
    List<Medication> findMedicationsByInteraction(String interaction);

    /**
     * 根据作用机制关键词查询药物
     *
     * @param mechanismKeyword 作用机制关键词
     * @return 药物列表
     */
    List<Medication> findMedicationsByMechanismContaining(String mechanismKeyword);

    /**
     * 更新药物信息
     *
     * @param medication 药物实体
     * @return 更新后的药物实体
     */
    Medication updateMedication(Medication medication);

    /**
     * 添加品牌名到药物
     *
     * @param medicationId 药物ID
     * @param brandNames   品牌名列表
     * @return 更新后的药物实体
     */
    Medication addBrandNamesToMedication(Long medicationId, List<String> brandNames);

    /**
     * 添加副作用到药物
     *
     * @param medicationId 药物ID
     * @param sideEffects  副作用集合
     * @return 更新后的药物实体
     */
    Medication addSideEffectsToMedication(Long medicationId, Set<String> sideEffects);

    /**
     * 添加禁忌症到药物
     *
     * @param medicationId      药物ID
     * @param contraindications 禁忌症集合
     * @return 更新后的药物实体
     */
    Medication addContraindicationsToMedication(Long medicationId, Set<String> contraindications);

    /**
     * 添加药物相互作用到药物
     *
     * @param medicationId 药物ID
     * @param interactions 药物相互作用集合
     * @return 更新后的药物实体
     */
    Medication addInteractionsToMedication(Long medicationId, Set<String> interactions);

    /**
     * 删除药物
     *
     * @param id 药物ID
     */
    void deleteMedication(Long id);

    /**
     * 删除药物品牌名
     *
     * @param medicationId 药物ID
     * @param brandName    品牌名
     * @return 更新后的药物实体
     */
    Medication removeBrandNameFromMedication(Long medicationId, String brandName);

    /**
     * 删除药物副作用
     *
     * @param medicationId 药物ID
     * @param sideEffect   副作用
     * @return 更新后的药物实体
     */
    Medication removeSideEffectFromMedication(Long medicationId, String sideEffect);

    /**
     * 删除药物禁忌症
     *
     * @param medicationId     药物ID
     * @param contraindication 禁忌症
     * @return 更新后的药物实体
     */
    Medication removeContraindicationFromMedication(Long medicationId, String contraindication);

    /**
     * 删除药物相互作用
     *
     * @param medicationId 药物ID
     * @param interaction  药物相互作用
     * @return 更新后的药物实体
     */
    Medication removeInteractionFromMedication(Long medicationId, String interaction);
}
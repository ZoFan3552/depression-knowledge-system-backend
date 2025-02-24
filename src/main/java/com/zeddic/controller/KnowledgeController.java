package com.zeddic.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.zeddic.common.response.ApiResponse;
import com.zeddic.entity.knowledge.*;
import com.zeddic.service.knowledge.*;
import com.zeddic.common.vo.ResponseCode;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@RestController
@RequestMapping("knowledge")
@CrossOrigin("*")
@RequiredArgsConstructor
@Validated
@Slf4j
public class KnowledgeController {
    private final CauseService causeService;
    private final DepressionService depressionService;
    private final DiagnosisService diagnosisService;
    private final MedicationService medicationService;
    private final PreventionService preventionService;
    private final SymptomService symptomService;
    private final TreatmentService treatmentService;

    // 通用响应处理方法
    private <T> ApiResponse<T> handleResponse(Supplier<T> dataSupplier) {
        T data = dataSupplier.get();
        return ApiResponse.success(
                ResponseCode.API_SUCCESS.getValue(),
                "接口调用成功",
                data
        );
    }

    // 通用Optional响应处理方法
    private <T> ApiResponse<T> handleOptionalResponse(Optional<T> optional) {
        T data = null;
        if (optional.isPresent()) {
            data = optional.get();
        }
        return ApiResponse.success(
                ResponseCode.API_SUCCESS.getValue(),
                "接口调用成功",
                data
        );
    }

    // 通用增加知识(结点和关系)响应处理办法
    private ApiResponse<Void> handleVoidResponse(Runnable operation) {
        operation.run();
        return ApiResponse.success(
                ResponseCode.API_SUCCESS.getValue(),
                "接口调用成功",
                null
        );
    }
    
    /*
    获取知识集合
     */
    @PostMapping("/get-all-depressions")
    @SaCheckLogin
    public ApiResponse<List<Depression>> getAllDepressionsNodes() {
        return handleResponse(depressionService::getAllDepressionNodes);
    }

    @PostMapping("/get-all-causes")
    @SaCheckLogin
    public ApiResponse<List<Cause>> getAllCausesNodes() {
        return handleResponse(causeService::getAllCausesNodes);
    }

    @PostMapping("/get-all-diagnosis")
    @SaCheckLogin
    public ApiResponse<List<Diagnosis>> getAllDiagnosesNodes() {
        return handleResponse(diagnosisService::getAllDiagnosisNodes);
    }

    @PostMapping("/get-all-medications")
    @SaCheckLogin
    public ApiResponse<List<Medication>> getAllMedicationsNodes() {
        return handleResponse(medicationService::getAllMedicationNodes);
    }

    @PostMapping("/get-all-preventions")
    @SaCheckLogin
    public ApiResponse<List<Prevention>> getAllPreventionsNodes() {
        return handleResponse(preventionService::getAllPreventionNodes);
    }

    @PostMapping("/get-all-symptoms")
    @SaCheckLogin
    public ApiResponse<List<Symptom>> getAllSymptomsNodes() {
        return handleResponse(symptomService::getAllSymptomNodes);
    }

    @PostMapping("/get-all-treatments")
    @SaCheckLogin
    public ApiResponse<List<Treatment>> getAllTreatmentsNodes() {
        return handleResponse(treatmentService::getAllTreatmentNodes);
    }

    /*
    获取单个知识
     */
    @PostMapping("/get-depression-by-name")
    @SaCheckLogin
    public ApiResponse<Depression> getDepressionNodeByName(@RequestBody @NotBlank String name) {
        return handleOptionalResponse(depressionService.getDepressionNodeByName(name));
    }

    @PostMapping("/get-cause-by-name")
    @SaCheckLogin
    public ApiResponse<Cause> getCauseNodeByName(@RequestBody @NotBlank String name) {
        return handleOptionalResponse(causeService.getCauseNodeByName(name));
    }

    @PostMapping("/get-diagnosis-by-name")
    @SaCheckLogin
    public ApiResponse<Diagnosis> getDiagnosisNodeByName(@RequestBody @NotBlank String name) {
        return handleOptionalResponse(diagnosisService.getDiagnosisById(name));
    }

    @PostMapping("/get-medication-by-name")
    @SaCheckLogin
    public ApiResponse<Medication> getMedicationNodeByName(@RequestBody @NotBlank String name) {
        return handleOptionalResponse(medicationService.getMedicationNodeByName(name));
    }

    @PostMapping("/get-prevention-by-name")
    @SaCheckLogin
    public ApiResponse<Prevention> getPreventionNodeByName(@RequestBody @NotBlank String name) {
        return handleOptionalResponse(preventionService.getPreventionNodeByName(name));
    }

    @PostMapping("/get-symptom-by-name")
    @SaCheckLogin
    public ApiResponse<Symptom> getSymptomNodeByName(@RequestBody @NotBlank @NotBlank String name) {
        return handleOptionalResponse(symptomService.getSymptomNodeByName(name));
    }

    @PostMapping("/get-treatment-by-name")
    @SaCheckLogin
    public ApiResponse<Treatment> getTreatmentNodeByName(@RequestBody @NotBlank String name) {
        return handleOptionalResponse(treatmentService.getTreatmentNodeByName(name));
    }

    /*
    增加知识
     */
    @PostMapping("/add-depression")
    @SaCheckLogin
    public ApiResponse<Void> addDepression(@RequestBody @NotBlank String depressionName) {
        return handleVoidResponse(() -> depressionService.addDepressionNode(depressionName));
    }

    @PostMapping("/add-cause")
    @SaCheckLogin
    public ApiResponse<Void> addCause(@RequestBody @NotBlank String causeName) {
        return handleVoidResponse(() -> causeService.addCauseNode(causeName));
    }
    
    @PostMapping("/add-diagnosis")
    @SaCheckLogin
    public ApiResponse<Void> addDiagnosis(@RequestBody @NotBlank String diagnosisName) {
        return handleVoidResponse(() -> diagnosisService.addDiagnosisNode(diagnosisName));
    }
    
    @PostMapping("/add-medication")
    @SaCheckLogin
    public ApiResponse<Void> addMedication(@RequestBody @NotBlank String medicationName) {
        return handleVoidResponse(() -> medicationService.addMedicationNode(medicationName));
    }

    @PostMapping("/add-prevention")
    @SaCheckLogin
    public ApiResponse<Void> addPrevention(@RequestBody @NotBlank String preventionName) {
        return handleVoidResponse(() -> preventionService.addPreventionNode(preventionName));
    }

    @PostMapping("/add-symptom")
    @SaCheckLogin
    public ApiResponse<Void> addSymptom(@RequestBody @NotBlank String symptomName) {
        return handleVoidResponse(() -> symptomService.addSymptomNode(symptomName));
    }

    @PostMapping("/add-treatment")
    @SaCheckLogin
    public ApiResponse<Void> addTreatment(@RequestBody @NotBlank String treatmentName) {
        return handleVoidResponse(() -> treatmentService.addTreatmentNode(treatmentName));
    }

    /*
    删除知识
     */
    @PostMapping("/delete-cause")
    @SaCheckLogin
    public ApiResponse<Void> deleteCause(@RequestBody @NotBlank String causeName) {
        return handleVoidResponse(() -> causeService.deleteCauseNodeByName(causeName));
    }

    @PostMapping("/delete-depression")
    @SaCheckLogin
    public ApiResponse<Void> deleteDepression(@RequestBody @NotBlank String depressionName) {
        return handleVoidResponse(() -> depressionService.deleteDepressionNodeByName(depressionName));
    }

    @PostMapping("/delete-diagnosis")
    @SaCheckLogin
    public ApiResponse<Void> deleteDiagnosis(@RequestBody @NotBlank String diagnosisName) {
        return handleVoidResponse(() -> diagnosisService.deleteDiagnosisNodeByName(diagnosisName));
    }

    @PostMapping("/delete-medication")
    @SaCheckLogin
    public ApiResponse<Void> deleteMedication(@RequestBody @NotBlank String medicationName) {
        return handleVoidResponse(() -> medicationService.deleteMedicationNodeByName(medicationName));
    }

    @PostMapping("/delete-prevention")
    @SaCheckLogin
    public ApiResponse<Void> deletePrevention(@RequestBody @NotBlank String preventionName) {
        return handleVoidResponse(() -> preventionService.deletePreventionNodeByName(preventionName));
    }

    @PostMapping("/delete-symptom")
    @SaCheckLogin
    public ApiResponse<Void> deleteSymptom(@RequestBody @NotBlank String symptomName) {
        return handleVoidResponse(() -> symptomService.deleteSymptomNodeByName(symptomName));
    }

    @PostMapping("/delete-treatment")
    @SaCheckLogin
    public ApiResponse<Void> deleteTreatment(@RequestBody @NotBlank String treatmentName) {
        return handleVoidResponse(() -> treatmentService.deleteTreatmentNodeByName(treatmentName));
    }
    
    /*
    增加结点关系
     */
    @PostMapping("/add-cause-relation")
    @SaCheckLogin
    public ApiResponse<Void> addCauseRelation(@RequestBody @NotBlank String depressionName,
                                              @RequestBody @NotBlank String causeName) {
        return handleVoidResponse(() -> depressionService.addCauseRelation(depressionName, causeName));
    }

    @PostMapping("/add-diagnosis-relation")
    @SaCheckLogin
    public ApiResponse<Void> addDiagnosisRelation(@RequestBody @NotBlank String depressionName,
                                                  @RequestBody @NotBlank String diagnosisName) {
        return handleVoidResponse(() -> depressionService.addDiagnosisRelation(depressionName, diagnosisName));
    }

    @PostMapping("/add-medication-relation")
    @SaCheckLogin
    public ApiResponse<Void> addMedicationRelation(@RequestBody @NotBlank String depressionName,
                                                   @RequestBody @NotBlank String medicationName) {
        return handleVoidResponse(() -> depressionService.addMedicationRelation(depressionName, medicationName));
    }

    @PostMapping("/add-prevention-relation")
    @SaCheckLogin
    public ApiResponse<Void> addPreventionRelation(@RequestBody @NotBlank String depressionName,
                                                   @RequestBody @NotBlank String preventionName) {
        return handleVoidResponse(() -> depressionService.addPreventionRelation(depressionName, preventionName));
    }

    @PostMapping("/add-symptom-relation")
    @SaCheckLogin
    public ApiResponse<Void> addSymptomRelation(@RequestBody @NotBlank String depressionName,
                                                @RequestBody @NotBlank String symptomName) {
        return handleVoidResponse(() -> depressionService.addSymptomRelation(depressionName, symptomName));
    }

    @PostMapping("/add-treatment-relation")
    @SaCheckLogin
    public ApiResponse<Void> addTreatmentRelation(@RequestBody @NotBlank String depressionName,
                                                  @RequestBody @NotBlank String treatmentName) {
        return handleVoidResponse(() -> depressionService.addTreatmentRelation(depressionName, treatmentName));
    }
}
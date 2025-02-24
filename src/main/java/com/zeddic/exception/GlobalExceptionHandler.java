package com.zeddic.exception;

import cn.dev33.satoken.exception.NotLoginException;
import com.zeddic.common.response.ApiResponse;
import com.zeddic.common.vo.ResponseCode;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.stream.Collectors;

/**
 * @author: zeddic
 * @description:
 * @date: 2025/1/24 下午1:03
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<String> handleInvalidArgs(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        String errorMessages = bindingResult.getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("; "));
        // 记录详细错误日志，但返回给前端的信息为“参数有误”
        return buildErrorResponse(ResponseCode.SYSTEM_ERROR, "参数有误", errorMessages);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResponse<String> handleConstraintViolation(ConstraintViolationException ex) {
        return buildErrorResponse(ResponseCode.UNKNOWN_ERROR, "系统检验错误", ex.getMessage());
    }

    @ExceptionHandler(NotLoginException.class)
    public ApiResponse<String> handleNotLogin(NotLoginException ex) {
        return buildErrorResponse(ResponseCode.API_UNAUTHORIZED, "接口调用失败:用户未登录", ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<String> handleRuntimeException(RuntimeException ex) {
        return buildErrorResponse(ResponseCode.API_FAILED, ex.getMessage(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<String> handleException(Exception ex) {
        return buildErrorResponse(ResponseCode.SYSTEM_ERROR, "未知错误:" + ex.getMessage(), ex.getMessage());
    }

    /**
     * 封装统一错误响应的构造逻辑
     *
     * @param code        响应码枚举
     * @param userMessage 返回给前端的错误信息
     * @param logMessage  用于记录日志的错误信息
     * @return ApiResponse
     */
    private ApiResponse<String> buildErrorResponse(ResponseCode code, String userMessage, String logMessage) {
        log.error(logMessage);
        return ApiResponse.error(code.getValue(), userMessage);
    }
}

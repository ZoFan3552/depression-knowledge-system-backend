package com.zeddic.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Zeddic
 * @description:
 * @date: 2025 /1/24 下午1:08
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private String code;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    /**
     * 成功响应
     * @param code    响应码
     * @param message 响应消息
     * @param data    数据体
     * @return 完整响应体
     */
    public static <T> ApiResponse<T> success(String code, String message, T data) {
        return new ApiResponse<>(code, message, data, LocalDateTime.now());
    }

    /**
     * 错误响应
     * @param code    响应码
     * @param message 响应消息
     * @return 完整响应体
     */
    public static <T> ApiResponse<T> error(String code, String message) {
        return new ApiResponse<>(code, message, null, LocalDateTime.now());
    }
}

package com.zeddic.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author: zeddic
 * @description:
 * @date: 2025/1/24 下午1:08
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private String code;           // 状态码
    private String message;       // 消息
    private T data;               // 数据
    private LocalDateTime timestamp;  // 时间戳

    // 成功响应
    public static <T> ApiResponse<T> success(String code, String message, T data) {
        return new ApiResponse<>(code, message, data, LocalDateTime.now());
    }

    // 错误响应
    public static <T> ApiResponse<T> error(String code, String message) {
        return new ApiResponse<>(code, message, null, LocalDateTime.now());
    }
}

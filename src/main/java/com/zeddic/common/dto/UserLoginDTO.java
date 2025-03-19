package com.zeddic.common.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * @author: zeddic
 * @description:
 * @date: 2025/1/24 下午5:05
 */
@Data
public class UserLoginDTO {
    @NotNull(message = "用户名不能为空")
    private String username;
    @NotNull(message = "密码不能为空")
    @Size(min = 6, message = "密码最小长度为6")
    private String password;
}

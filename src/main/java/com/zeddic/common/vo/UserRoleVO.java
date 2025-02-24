package com.zeddic.common.vo;

import lombok.Getter;

/**
 * @author: zeddic
 * @description:
 * @date: 2025/1/23 下午9:05
 */
@Getter
public enum UserRoleVO {
    ADMIN("admin"),
    USER("user");

    private final String value;

    UserRoleVO(String value) {
        this.value = value;
    }
}

package com.zeddic.common.vo;

import lombok.Getter;

/**
 * The enum User role vo.
 *
 * @author Zeddic
 * @description:
 * @date: 2025 /1/23 下午9:05
 */
@Getter
public enum UserRoleVO {
    /**
     * 管理员角色
     */
    ADMIN("admin"),
    /**
     * 普通用户角色
     */
    USER("user");

    private final String value;

    UserRoleVO(String value) {
        this.value = value;
    }
}

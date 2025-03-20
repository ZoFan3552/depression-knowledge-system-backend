package com.zeddic.common.vo;

import lombok.Getter;

/**
 * @author Zeddic
 * @description:
 * @date: 2025/1/24 下午8:30
 */
@Getter
public enum ResponseCode {
    //登陆相关状态码
    LOGIN_SUCCEED("10000"),
    LOGIN_FAILED("10001"),
    LOGOUT_SUCCEED("10002"),
    LOGOUT_FAILED("10003"),
    LOGIN_ALREADY_DONE("10004"),
    LOGIN_UNDONE("10005"),
    //注册相关状态码
    REGISTER_SUCCEED("20000"),
    REGISTER_FAILED_USERNAME_EXISTED("20001"),
    REGISTER_FAILED_EMAIL_EXISTED("20002"),
    //系统发生错误
    SYSTEM_ERROR("30001"),
    //未知错误
    UNKNOWN_ERROR("40001"),
    //API调用相关
    API_SUCCESS("50001"),
    API_FAILED("50002"),
    API_UNAVAILABLE("50003"),
    API_UNAUTHORIZED("50004"),
    ;

    private final String value;
    ResponseCode(String value) {
        this.value = value;
    }
}

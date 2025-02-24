package com.zeddic.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.zeddic.common.response.ApiResponse;
import com.zeddic.common.dto.TokenDTO;
import com.zeddic.common.dto.UserLoginDTO;
import com.zeddic.entity.user.User;
import com.zeddic.common.vo.ResponseCode;
import com.zeddic.common.vo.UserRoleVO;
import com.zeddic.service.user.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author: zeddic
 * @description:
 * @date: 2025/1/24 下午1:21
 */
@RestController
@RequestMapping("user")
@CrossOrigin("*")
@Slf4j
public class UserController {

    private final UserService userService;

    // 静态常量：SHA-256 加密工具，避免每次请求都创建实例
    private static final Digester SHA256_DIGESTER = new Digester(DigestAlgorithm.SHA256);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ApiResponse<String> register(@Valid @RequestBody User user) {
        // 检查用户名是否已存在
        if (userService.checkUsernameHasExisted(user.getUsername())) {
            return ApiResponse.error(
                    ResponseCode.REGISTER_FAILED_USERNAME_EXISTED.getValue(),
                    "注册失败,用户名已存在"
            );
        }
        // 检查邮箱是否已存在
        if (userService.checkEmailHasExisted(user.getEmail())) {
            return ApiResponse.error(
                    ResponseCode.REGISTER_FAILED_EMAIL_EXISTED.getValue(),
                    "注册失败,该邮箱已被使用"
            );
        }

        // 使用 SHA-256 加密密码
        user.setPassword(SHA256_DIGESTER.digestHex(user.getPassword()));
        // 设置默认角色为 "user"
        user.setRole("user");
        // 同时设置创建时间和更新时间
        LocalDateTime now = LocalDateTime.now();
        user.setCreateTime(now);
        user.setUpdateTime(now);

        int count = userService.insertUser(user);
        if (count > 0) {
            return ApiResponse.success(
                    ResponseCode.REGISTER_SUCCEED.getValue(),
                    "注册成功",
                    null
            );
        }
        return ApiResponse.error(
                ResponseCode.UNKNOWN_ERROR.getValue(),
                "注册失败,未知错误,请联系管理员"
        );
    }

    @PostMapping("/login")
    public ApiResponse<TokenDTO> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        // 已登录则直接返回错误提示
        if (StpUtil.isLogin()) {
            return ApiResponse.error(
                    ResponseCode.LOGIN_ALREADY_DONE.getValue(),
                    "您已登录,请勿重复登录!"
            );
        }

        String username = userLoginDTO.getUsername();
        // 加密传入的密码
        String password = SHA256_DIGESTER.digestHex(userLoginDTO.getPassword());
        User origin = userService.selectUserByUsername(username);
        if (origin != null && origin.getPassword().equals(password)) {
            // 登录操作
            StpUtil.login(username);
            TokenDTO tokenDTO = new TokenDTO(
                    StpUtil.getTokenName(),
                    StpUtil.getTokenValue(),
                    StpUtil.getTokenTimeout()
            );
            String roleDesc = origin.getRole().equals(UserRoleVO.ADMIN.getValue()) ? "管理员" : "用户";
            String message = "登陆成功!欢迎" + roleDesc + " " + origin.getUsername();
            return ApiResponse.success(
                    ResponseCode.LOGIN_SUCCEED.getValue(),
                    message,
                    tokenDTO
            );
        }
        return ApiResponse.error(
                ResponseCode.LOGIN_FAILED.getValue(),
                "登陆失败,请检查用户名和密码"
        );
    }

    @PostMapping("/logout")
    public ApiResponse<String> logout() {
        if (StpUtil.isLogin()) {
            StpUtil.logout();
            return ApiResponse.success(
                    ResponseCode.LOGOUT_SUCCEED.getValue(),
                    "注销成功",
                    null
            );
        }
        return ApiResponse.error(
                ResponseCode.LOGOUT_FAILED.getValue(),
                "注销失败,当前没有账户登录"
        );
    }

    @PostMapping("/change/password")
    @SaCheckLogin
    public ApiResponse<String> changePassword() {
        // TODO: 待接入邮箱验证码后实现密码修改功能
        return ApiResponse.error(
                ResponseCode.UNKNOWN_ERROR.getValue(),
                "功能未实现"
        );
    }
}

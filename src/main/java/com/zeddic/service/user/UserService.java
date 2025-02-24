package com.zeddic.service.user;

import com.zeddic.entity.user.User;

/**
 * @author: zeddic
 * @description:
 * @date: 2025/1/23 下午9:23
 */

public interface UserService {
    /**
     * 插入用户
     */
    int insertUser(User user);
    /**
     * 根据用户名查询用户
     */
    User selectUserByUsername(String username);
    boolean checkUsernameHasExisted(String username);
    boolean checkEmailHasExisted(String email);
    /**
     * 根据邮箱查询用户
     */
    User selectUserByEmail(String email);
}

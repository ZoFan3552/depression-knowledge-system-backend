package com.zeddic.service.user;

import com.zeddic.entity.user.User;

/**
 * @author Zeddic
 * @description:
 * @date: 2025 /1/23 下午9:23
 */
public interface UserService {
    /**
     * 插入用户
     *
     * @param user the user
     * @return the int
     */
    int insertUser(User user);

    /**
     * 根据用户名查询用户
     *
     * @param username the username
     * @return the user
     */
    User selectUserByUsername(String username);

    /**
     * Check username has existed boolean.
     *
     * @param username the username
     * @return the boolean
     */
    boolean checkUsernameHasExisted(String username);

    /**
     * Check email has existed boolean.
     *
     * @param email the email
     * @return the boolean
     */
    boolean checkEmailHasExisted(String email);

    /**
     * 根据邮箱查询用户
     *
     * @param email the email
     * @return the user
     */
    User selectUserByEmail(String email);
}

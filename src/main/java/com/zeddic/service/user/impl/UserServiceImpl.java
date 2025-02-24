package com.zeddic.service.user.impl;

import com.zeddic.entity.user.User;
import com.zeddic.repository.user.UserMapper;
import com.zeddic.service.user.UserService;
import org.springframework.stereotype.Service;

/**
 * @author: zeddic
 * @description:
 * @date: 2025/1/23 下午9:24
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User selectUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public boolean checkUsernameHasExisted(String username) {
        return selectUserByUsername(username) != null;
    }

    @Override
    public boolean checkEmailHasExisted(String email) {
        return selectUserByEmail(email) != null;
    }

    @Override
    public User selectUserByEmail(String email) {
        return userMapper.selectByEmail(email);
    }
}

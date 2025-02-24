package com.zeddic.repository.user;


import com.zeddic.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: zeddic
 * @description:
 * @date: 2025/1/23 下午9:06
 */
@Mapper
public interface UserMapper {
    /**
     * 插入用户
     */
    int insert(User user);

    /**
     * 根据ID更新用户
     */
    int updateByUsername(User user);

    /**
     * 根据用户名查询用户
     */
    User selectByUsername(String username);

    /**
     * 根据邮箱查询用户
     */
    User selectByEmail(String email);

    /**
     * 删除用户
     */
    int deleteByUsername(String username);
}
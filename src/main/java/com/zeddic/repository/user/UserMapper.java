package com.zeddic.repository.user;


import com.zeddic.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Zeddic
 * @description:
 * @date: 2025 /1/23 下午9:06
 */
@Mapper
public interface UserMapper {
    /**
     * 插入用户
     *
     * @param user 用户实体
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 根据ID更新用户
     *
     * @param user 用户实体
     * @return 影响行数
     */
    int updateByUsername(User user);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户实体
     */
    User selectByUsername(String username);

    /**
     * 根据邮箱查询用户
     *
     * @param email 邮箱
     * @return 用户实体
     */
    User selectByEmail(String email);

    /**
     * 删除用户
     *
     * @param username 用户名
     * @return 影响行数
     */
    int deleteByUsername(String username);
}
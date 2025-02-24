package com.zeddic.user;

import com.zeddic.entity.user.User;
import com.zeddic.repository.user.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: zeddic
 * @description:
 * @date: 2025/1/23 下午9:13
 */
@SpringBootTest
@Slf4j
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        // 创建用户实体对象
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("password");
        user.setRole("user");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        // 插入用户
        int result = userMapper.insert(user);

        // 验证插入结果
        assertEquals(1, result);  // 期望插入成功，返回1
    }

    @Test
    public void testSelectByUsername() {
        String username = "Zeddic";
        User user = userMapper.selectByUsername(username);
        log.info("测试结果:{}", user);
    }

    @Test
    public void testSelectByEmail() {
        String email = "testuser@example.com";
        User user = userMapper.selectByEmail(email);

        // 验证查询结果
        assertNotNull(user);
        assertEquals(email, user.getEmail());  // 期望查询到的邮箱与传入的邮箱相同
    }


}

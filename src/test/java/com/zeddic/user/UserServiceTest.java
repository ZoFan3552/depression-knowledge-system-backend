package com.zeddic.user;

import com.zeddic.entity.user.User;
import com.zeddic.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: zeddic
 * @description:
 * @date: 2025/1/24 下午6:34
 */
@SpringBootTest
@Slf4j
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testSelect() {
        User origin = userService.selectUserByUsername("Zeddic");
        log.info("测试结果:{}", origin);
    }
}

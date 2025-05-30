package com.zeddic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Zeddic
 */
@SpringBootApplication
@MapperScan("com.zeddic.repository.user")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

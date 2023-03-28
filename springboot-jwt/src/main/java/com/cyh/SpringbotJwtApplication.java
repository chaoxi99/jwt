package com.cyh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cyh.dao")
public class SpringbotJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbotJwtApplication.class, args);
    }

}

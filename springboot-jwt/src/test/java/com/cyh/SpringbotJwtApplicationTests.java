package com.cyh;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.HashMap;

@SpringBootTest
class SpringbotJwtApplicationTests {

    @Test
    void contextLoads() {
        // 头部需要一个Map集合
        HashMap<String, Object> map = new HashMap<>();
        //  过期时间需要一个Date时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE, 5);   // 20秒
        System.out.println("instance = " + instance.getTime());
        // 创建一个Jwt的令牌
        String Token = JWT.create()
                .withHeader(map)    // header
                .withClaim("userId", 21)        // payload
                .withClaim("userName", "zhangSan")       // 可存放多个非敏感信息
                .withExpiresAt(instance.getTime())      // 指定令牌的过期时间（令牌不需要一直存在）
                .sign(Algorithm.HMAC256("123456"));// signature 算法
        System.out.println("Token = " + Token);//eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6InpoYW5nU2FuIiwiZXhwIjoxNjc5NzIzODAyLCJ1c2VySWQiOjIxfQ.IhHhGYkfGh-ecK2u2b7-F9K-x-k4nN82QyCRTvmPuBw
    }

    @Test
    void test2() {
        // 创建验证对象
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("123456")).build();
        // 验证token,获得一个解码信息
        DecodedJWT decodedJWT = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6InpoYW5nU2FuIiwiZXhwIjoxNjc5NzI0MjgzLCJ1c2VySWQiOjIxfQ.6iJfVF8aj0hzS-l1IvptaC1ZrKes30YiluGfqgdwlU0");

        //通过解码信息获取到请求头、
        String header = decodedJWT.getHeader();
        System.out.println("header = " + header);
        String payload = decodedJWT.getPayload();
        System.out.println("payload = " + payload);
        String userId = decodedJWT.getClaim("userId").asString();
        System.out.println("userId = " + userId);
        String signature = decodedJWT.getSignature();
        System.out.println("signature = " + signature);

    }
}

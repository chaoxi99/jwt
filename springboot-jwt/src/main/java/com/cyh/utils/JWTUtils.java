package com.cyh.utils;

/*JWT封装工具类*/

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {

    private static final String SIGN = "abc";

    // 生成token
    public static String getToken(Map<String, String> map) {

        // 创建一个请求头（默认）
        // HashMap<String, Object> headerMap = new HashMap<>();

        // 创建一个Date日期，方便设置过期时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7);

        // 创建Token对象
        JWTCreator.Builder builder = JWT.create();

        // payload
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });
        String token = builder
                .withExpiresAt(instance.getTime())      // 设置过期时间
                .sign(Algorithm.HMAC256(SIGN));// 签名    signature
        return token;
    }



    // 验证Token的合法性
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }

/*    // 获取Token中的信息
    public static DecodedJWT getTokenInfo(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        return verify;
    }*/


}

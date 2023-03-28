package com.cyh.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cyh.entity.User;
import com.cyh.service.UserService;
import com.cyh.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/login")
    public Map<String, Object> login(User user) {
        Map<String, Object> resultMap = new HashMap<>();

        log.info("用户名[{}]", user.getUsername());
        log.info("密码[{}]", user.getPassword());
        try {
            User userDB = userService.login(user);
            Map<String, String> payload = new HashMap<>();
            payload.put("id", userDB.getId().toString());
            payload.put("name", userDB.getUsername());
            String token = JWTUtils.getToken(payload);

            resultMap.put("status", true);
            resultMap.put("msg", "认证成功");
            resultMap.put("token", token);
        } catch (Exception e) {
            resultMap.put("status", false);
            resultMap.put("msg", "认证失败");
        }
        return resultMap;
    }


    @PostMapping("/user/test")
    public Map<String, Object> test(String token) {
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("status", true);
        resultMap.put("msg", "请求成功");

        return resultMap;
    }
}

package com.cyh.interceptor;


import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.cyh.utils.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        String token = request.getHeader("token");
        try {
            JWTUtils.verify(token);
            resultMap.put("status", true);
            resultMap.put("msg", "请求成功");
            return true;
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            resultMap.put("msg", "无效签名");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            resultMap.put("msg", "Token已过期...");
        }
        resultMap.put("status", false);
        // 将Map转换为JSON jsckson
        String json = new ObjectMapper().writeValueAsString(resultMap);
        response.setContentType("application/json;charset=UTF_8");
        response.getWriter().println(json);

        return false;
    }
}

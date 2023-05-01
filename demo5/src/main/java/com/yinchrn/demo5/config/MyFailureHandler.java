package com.yinchrn.demo5.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yinchrn.demo5.pojo.Param;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class MyFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(new Param("error","账号或密码错误"));
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
    }
}

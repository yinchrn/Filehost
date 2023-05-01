package com.yinchrn.demo5.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yinchrn.demo5.pojo.Param;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class MySuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(new Param("success","登陆成功"));
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
    }
}

package com.yinchrn.demo5.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yinchrn.demo5.pojo.Param;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(new Param("error","权限不足"));
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
    }
}

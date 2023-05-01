package com.yinchrn.demo5.controller;


import com.yinchrn.demo5.pojo.Param;
import com.yinchrn.demo5.pojo.UserAccount;
import com.yinchrn.demo5.service.UserService;
import com.yinchrn.demo5.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/User")
public class UserController {
    private final UserService userService;
    @Autowired
    UserController(UserServiceImpl userService){
        this.userService = userService;
    }

    @PostMapping("/reg")
    Param register(@RequestBody UserAccount userAccount){
        if (userAccount.getUsername().isEmpty()||userAccount.getPassword().isEmpty()){
            return new Param("error","用户名或密码不能为空");
        }
        if (userService.register(userAccount))
        {
            return new Param("success","注册成功");
        }
        return new Param("error","用户名已存在");
    }

    @GetMapping("/MyAccount")
    UserAccount getMyAccount(){
        return userService.MyAccount();
    }

    @DeleteMapping("/delete")
    Param deleteMyAccount() throws IOException {
        userService.deleteMyAccount();
        return new  Param("success","注销成功");
    }
}

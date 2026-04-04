package com.bit.springblogdemo.controller;


import com.bit.springblogdemo.pojo.request.UserLoginRequest;
import com.bit.springblogdemo.pojo.response.UserInfoResponse;
import com.bit.springblogdemo.pojo.response.UserLoginResponse;
import com.bit.springblogdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping("/login")
    public UserLoginResponse login(@Validated @RequestBody UserLoginRequest LoginRequest){
        log.info("用户登录： 用户名 {}",LoginRequest.getUserName());
        return userService.login(LoginRequest);
    }

    @RequestMapping("/getUserInfo")
    public UserInfoResponse getUserInfo(Integer id){
        return userService.getUserInfo(id);
    }


}

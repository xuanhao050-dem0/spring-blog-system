package com.bit.springblogdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bit.springblogdemo.common.Assert.BlogAssert;
import com.bit.springblogdemo.common.constants.Constant;
import com.bit.springblogdemo.common.utils.JWTUtil;
import com.bit.springblogdemo.mapper.UserMapper;
import com.bit.springblogdemo.pojo.dataobject.UserInfo;
import com.bit.springblogdemo.pojo.request.UserLoginRequest;
import com.bit.springblogdemo.pojo.response.UserInfoResponse;
import com.bit.springblogdemo.pojo.response.UserLoginResponse;
import com.bit.springblogdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public UserLoginResponse login(UserLoginRequest loginRequest) {
        //获取用户信息

        UserInfo userInfo = userMapper.selectOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getUserName, loginRequest.getUserName())
                //.eq(UserInfo::getPassword,loginRequest.getPassword())
                .eq(UserInfo::getDeleteFlag, Constant.UNDELETE)
        );
        //根据账号密码校验是否登录

        //在数据库中没找到这个用户名
        BlogAssert.notNull(userInfo, "用户名不存在");

        //isTrue里写成立条件
        BlogAssert.isTrue(userInfo.getId()>0,"用户名不合法");

        //检验给的密码是否合法
        BlogAssert.hasText(loginRequest.getPassword(),"密码为空");

        //检验输入密码和数据库密码是否一致
        BlogAssert.isTrue(loginRequest.getPassword().equals(userInfo.getPassword()),"密码错误");

        //生成token
        Map<String,Object> claims =new HashMap<>();
        claims.put("id",userInfo.getId());
        claims.put("username",userInfo.getUserName());
        String token = JWTUtil.generateToken(claims);

        return new UserLoginResponse(userInfo.getId(),token);
    }

    @Override
    public UserInfoResponse getUserInfo(Integer id) {
        UserInfo userInfo = userMapper.selectOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getId, id)
                .eq(UserInfo::getDeleteFlag, Constant.UNDELETE)
        );



        return new UserInfoResponse(userInfo.getId(), userInfo.getUserName(), userInfo.getGithubUrl());
    }
}

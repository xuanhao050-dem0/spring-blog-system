package com.bit.springblogdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bit.springblogdemo.common.constants.Constant;
import com.bit.springblogdemo.mapper.UserMapper;
import com.bit.springblogdemo.pojo.dataobject.UserInfo;
import com.bit.springblogdemo.pojo.request.UserLoginRequest;
import com.bit.springblogdemo.pojo.response.UserLoginResponse;
import com.bit.springblogdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .eq(UserInfo::getDeleteFlag, Constant.DELETE)
        );
        //根据账号密码校验是否登录

        return null;
    }
}

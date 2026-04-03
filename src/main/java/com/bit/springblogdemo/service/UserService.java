package com.bit.springblogdemo.service;

import com.bit.springblogdemo.pojo.request.UserLoginRequest;
import com.bit.springblogdemo.pojo.response.UserLoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserLoginResponse login(UserLoginRequest loginRequest);
}

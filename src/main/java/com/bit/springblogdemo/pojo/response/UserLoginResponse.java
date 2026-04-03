package com.bit.springblogdemo.pojo.response;

import lombok.Data;

@Data
public class UserLoginResponse {
    public Integer id;
    public String token;
}

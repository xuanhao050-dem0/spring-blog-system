package com.bit.springblogdemo.pojo.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserLoginRequest {
    @NotBlank(message = "用户名不能为空")
    @Length(max = 20, message = "长度不能超过20")
    public String userName;
    @NotBlank(message = "密码不能为空")
    @Length(max = 20, message = "长度不能超过20")
    public String password;
}

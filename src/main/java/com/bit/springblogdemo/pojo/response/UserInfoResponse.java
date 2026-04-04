package com.bit.springblogdemo.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInfoResponse {

    private Integer id;
    private String userName;

    private String githubUrl;




}

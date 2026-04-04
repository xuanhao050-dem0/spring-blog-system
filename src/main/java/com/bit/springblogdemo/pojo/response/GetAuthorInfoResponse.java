package com.bit.springblogdemo.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAuthorInfoResponse {

    private Integer id;
    private String userName;

    private String githubUrl;
}

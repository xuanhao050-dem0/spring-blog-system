package com.bit.springblogdemo.pojo.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 返回博客信息所需要的属性
 */
@Data
public class BlogInfoResponse {
    private Integer id;
    private String title;

    private Integer userId;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;


}

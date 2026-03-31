package com.bit.springblogdemo.common.exeception;

import lombok.Data;

/**
 * 自定义异常
 */
@Data
public class BlogException extends RuntimeException {
    private Integer code;
    private String message;

    public BlogException(String message) {

        this.message = message;
    }

    public BlogException(String message, Integer code) {

        this.code = code;
        this.message = message;
    }
}

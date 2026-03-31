package com.bit.springblogdemo.common.advice;


import com.bit.springblogdemo.common.exeception.BlogException;
import com.bit.springblogdemo.pojo.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler
    public Result handler(Exception e){
        log.error("发生异常,e:",e);
        //实际开发不返回e
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler
    public Result handler(BlogException e){
        log.error("发生异常,e:",e);
        //实际开发不返回e
        return Result.fail(e.getMessage());
    }




}

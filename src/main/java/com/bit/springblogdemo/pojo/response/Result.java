package com.bit.springblogdemo.pojo.response;

import com.bit.springblogdemo.common.enums.ResultCodeEnums;
import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private String errMsg;
    private T data;

    public static <T> Result success(T data){
        Result result =new Result();
        result.setCode(ResultCodeEnums.SUCCESS.getCode());
        result.setData(data);
        return result;

    }

    public static <T> Result fail(String errMsg){
        Result result =new Result();
        result.setCode(ResultCodeEnums.FAIL.getCode());
        result.setErrMsg(errMsg);
        return result;

    }


}

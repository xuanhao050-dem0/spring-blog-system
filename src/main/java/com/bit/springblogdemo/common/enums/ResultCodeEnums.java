package com.bit.springblogdemo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ResultCodeEnums {
    SUCCESS(200),
    FAIL(-1)
    ;


    @Getter
    private int code;


}

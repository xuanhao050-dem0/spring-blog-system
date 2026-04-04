package com.bit.springblogdemo.common.Assert;

import com.bit.springblogdemo.common.exeception.BlogException;

public class BlogAssert {
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new BlogException(message);
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new BlogException(message);
        }
    }

    public static void hasText(Object object, String message) {
        if (object == null) {
            throw new BlogException(message);
        }
    }

}

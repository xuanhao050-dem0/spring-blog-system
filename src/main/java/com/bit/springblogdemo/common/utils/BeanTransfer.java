package com.bit.springblogdemo.common.utils;

import com.bit.springblogdemo.pojo.dataobject.BlogInfo;
import com.bit.springblogdemo.pojo.response.BlogInfoResponse;
import org.springframework.beans.BeanUtils;

public class BeanTransfer {
    public static BlogInfoResponse Transfer(BlogInfo blogInfo){
        BlogInfoResponse blogInfoResponse = new BlogInfoResponse();
        BeanUtils.copyProperties(blogInfo, blogInfoResponse);
        return blogInfoResponse;
    }
}

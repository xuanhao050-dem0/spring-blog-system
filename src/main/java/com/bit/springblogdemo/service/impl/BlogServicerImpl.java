package com.bit.springblogdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bit.springblogdemo.common.constants.Constant;
import com.bit.springblogdemo.mapper.BlogMapper;
import com.bit.springblogdemo.pojo.dataobject.BlogInfo;
import com.bit.springblogdemo.pojo.response.BlogInfoResponse;
import com.bit.springblogdemo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("service")
public class BlogServicerImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;

    public List<BlogInfoResponse> getBlogList() {
        //从数据库中找数据
        List<BlogInfo> blogInfos = blogMapper.selectList(new LambdaQueryWrapper<BlogInfo>()
                .eq(BlogInfo::getDeleteFlag, Constant.UNDELETE)
                .orderByDesc(BlogInfo::getId));

        //将获取数据转换为响应数据

    }
}

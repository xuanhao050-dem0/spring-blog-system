package com.bit.springblogdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bit.springblogdemo.common.constants.Constant;
import com.bit.springblogdemo.common.utils.BeanTransfer;
import com.bit.springblogdemo.mapper.BlogMapper;
import com.bit.springblogdemo.pojo.dataobject.BlogInfo;
import com.bit.springblogdemo.pojo.response.BlogInfoResponse;
import com.bit.springblogdemo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("service")
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;

    public List<BlogInfoResponse> getBlogList() {
        //从数据库中找数据
        List<BlogInfo> blogInfos = blogMapper.selectList(new LambdaQueryWrapper<BlogInfo>()
                .eq(BlogInfo::getDeleteFlag, Constant.UNDELETE)
                .orderByDesc(BlogInfo::getId));

        //将获取数据 转换为 响应数据
        List<BlogInfoResponse> blogInfoResponses = blogInfos.stream()
                .map(blogInfo -> BeanTransfer.Transfer(blogInfo))
                .collect(Collectors.toList());
        return blogInfoResponses;
    }

    public BlogInfoResponse getBlogDetail(Integer id){
        BlogInfo blogInfo = blogMapper.selectOne(new LambdaQueryWrapper<BlogInfo>()
                .eq(BlogInfo::getId, id)
                .eq(BlogInfo::getDeleteFlag, Constant.UNDELETE)
        );

        return BeanTransfer.Transfer(blogInfo);

    }



}

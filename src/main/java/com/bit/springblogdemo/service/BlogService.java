package com.bit.springblogdemo.service;

import com.bit.springblogdemo.pojo.response.BlogInfoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogService {
    List<BlogInfoResponse> getBlogList();

    BlogInfoResponse getBlogDetail(Integer id);
}

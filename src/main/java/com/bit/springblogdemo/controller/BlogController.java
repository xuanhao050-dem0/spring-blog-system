package com.bit.springblogdemo.controller;

import com.bit.springblogdemo.pojo.dataobject.BlogInfo;
import com.bit.springblogdemo.pojo.response.BlogInfoResponse;
import com.bit.springblogdemo.service.BlogService;
import com.bit.springblogdemo.service.impl.BlogServicerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {
    /**
     * 注入的多种方法
     * 1.Autowired
     * 2.构造方法
     */
    @Autowired
    BlogService blogService;

//    private final BlogServicerImpl blogServicer;
//
//    public BlogController(BlogServicerImpl blogServicer) {
//        this.blogServicer = blogServicer;
//    }

    @RequestMapping("/getList")
    public List<BlogInfoResponse> getBlogList(){
        return blogService.getBlogList();

    }
}

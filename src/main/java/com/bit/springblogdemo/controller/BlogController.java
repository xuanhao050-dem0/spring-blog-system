package com.bit.springblogdemo.controller;

import com.bit.springblogdemo.pojo.response.BlogInfoResponse;
import com.bit.springblogdemo.service.impl.BlogServiceImpl;
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
    BlogServiceImpl blogServiceImpl;

//    private final BlogServicerImpl blogServicer;
//
//    public BlogController(BlogServicerImpl blogServicer) {
//        this.blogServicer = blogServicer;
//    }

    @RequestMapping("/getList")
    public List<BlogInfoResponse> getBlogList(){
        return blogServiceImpl.getBlogList();

    }

    @RequestMapping("/getBlogDetail")
    public BlogInfoResponse getBlogDetail(Integer id){
        return blogServiceImpl.getBlogDetail(id);
    }
}

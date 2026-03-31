package com.bit.springblogdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bit.springblogdemo.pojo.dataobject.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserInfo> {
}

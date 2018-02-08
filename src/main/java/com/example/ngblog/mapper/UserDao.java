package com.example.ngblog.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * Created by 张文强
 * 2018/1/17 18:26
 */
@Mapper
public interface UserDao {
    public Object getUserInfo(String username);
}

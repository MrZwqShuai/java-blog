package com.example.ngblog.controller;

import com.example.ngblog.entity.Result;
import com.example.ngblog.mapper.UserDao;
import com.example.ngblog.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 张文强
 * 2018/1/17 18:29
 */
@RestController
public class UserController {

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public Result getUserInfo(@PathVariable String username) {
        return ResultUtil.success(userDao.getUserInfo(username));
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "hellow world";
    }
}

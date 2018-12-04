package com.example.ngblog.controller;

import com.example.ngblog.entity.Result;
import com.example.ngblog.service.WyMusicService;
import com.example.ngblog.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 张文强
 * 2018/7/4 8:59
 */
@RestController
public class WyMusicController {

    @Autowired
    public WyMusicService wyMusicService;

    @RequestMapping(value = "/banner/all", method = RequestMethod.GET)
    public Result getBanners() {
        return ResultUtil.success(wyMusicService.getBanners());
    }

}

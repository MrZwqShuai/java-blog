package com.example.ngblog.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 张文强
 * 2018/7/4 9:01
 */
@Service
public class WyMusicServiceImpl implements WyMusicService{

    public List<Map<String, Object>> getBanners() {
        List list = new ArrayList();
        String[] banners = new String[]{
                "http://47.98.137.213/wymusic/hot-music-1.jpg",
                "http://47.98.137.213/wymusic/hot-music-2.jpg",
                "http://47.98.137.213/wymusic/hot-music-3.jpg"
        };
        for (String banner: banners) {
            System.out.println(banner + "----");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("path", banner);
            list.add(map);
        }
        return list;
    }
}

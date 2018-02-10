package com.example.ngblog.controller;

import com.alibaba.fastjson.JSON;
import com.example.ngblog.entity.Article;
import com.example.ngblog.entity.Meta;
import com.example.ngblog.entity.Result;
import com.example.ngblog.mapper.ArticleDao;
import com.example.ngblog.service.ArticleService;
import com.example.ngblog.util.ResultUtil;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 张文强
 * 2018/1/23 15:05
 */

@RestController
public class ArticleController {

    @Autowired
    public ArticleService articleService;

    //根据用户查询所有文章
    @RequestMapping(value = "/article/author/{uid}", method = RequestMethod.GET)
    public Result getArticleByUser(@PathVariable Integer uid) {

        List<Map<String,Object>> list = articleService.getArticleByUser(uid);
        return ResultUtil.success(list);
    }

    //查询某个用户的文章总数
    @RequestMapping(value = "/article/total/{id}", method = RequestMethod.GET)
    public Result getArticleTotalByUser(@PathVariable Integer id) {
        return ResultUtil.success(articleService.getArticleTotalByUser(id));
    }

    //根据文章id查询某一篇文章
    @RequestMapping(value = "/article/getOneArticle", method = RequestMethod.GET, params = "articleId")
    public Result getOneArticleById(Integer articleId) {
        //增加pv
        return ResultUtil.success(articleService.getOneArticleDetails(articleId));
    }

    // 查询所有标签
    @RequestMapping(value = "/meta", method = RequestMethod.GET)
    public Result getAllMeta() {
        List<Map<String, Meta>> metaAll = articleService.getAllMeta();
        return ResultUtil.success(metaAll);
    }

    //通过标签获取文章列表
    @RequestMapping(value = "/t/{tagId}" , method = RequestMethod.GET)
    public Result getArticleByMeta(@PathVariable Integer tagId) {
        List<Map<String, Article>> tagArticle = articleService.getArticleByMeta(tagId);
        return ResultUtil.success(tagArticle);
    }

    //爬虫
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void crawlerArticle(String url, String target) throws IOException {
        articleService.crawlerArticle(url, target);
    }

    //为文章增加标签
    @RequestMapping(value = "/article/meta", method = RequestMethod.POST)
    public void postArticleMeta(Integer article_id, Integer tag_id) {
        articleService.postMeta(article_id, tag_id);
    }

    //用户添加一篇文章
    @RequestMapping(value = "/article/add", method = RequestMethod.POST)
    public Result postOneArticleByUser(@RequestBody String body) {

        Map<String, Object> m1 = (Map<String, Object>) JSON.parse(body);
        Map<String, Object> postBody = new HashMap<String, Object>();
        for (String s: m1.keySet()) {
            System.out.println(s + "==>" + m1.get(s));
            postBody.put(s,m1.get(s));
        }
        System.out.println(postBody);
        articleService.postOneArticleByUser(postBody);
        return ResultUtil.success(null);
    }

    //上传图片
    @RequestMapping(value = "/article/img/add", method = RequestMethod.POST)
    public Result uploadArticleImg(MultipartFile file, HttpServletRequest request) {
        Map<String,Object> filePath = articleService.uploadArticleImg(file, request);
        return ResultUtil.success(filePath);
    }


}

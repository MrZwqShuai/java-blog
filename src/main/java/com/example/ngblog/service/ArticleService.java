package com.example.ngblog.service;

import com.example.ngblog.entity.Article;
import com.example.ngblog.entity.Meta;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by 张文强
 * 2018/2/1 9:24
 */
public interface ArticleService {

    //查询用户所有文章列表
    List<Map<String, Object>> getArticleByUser(Integer uid);

    //查询用户文章总数
    Integer getArticleTotalByUser(Integer id);

    //展现某一篇文章
    Map<String, Object> getOneArticleDetails(Integer articleId);


    //获得文章所有标签
    List<Map<String, Meta>> getAllMeta();

    //根据标签查询文章
    List<Map<String, Article>> getArticleByMeta(Integer tagId);

    //爬虫文章
    void crawlerArticle(String url, String target) throws IOException;

    //为文章添加标签
    void postMeta(Integer articleId, Integer tagId );

    //为用户增加一片文章
    void postOneArticleByUser(Map<String, Object> article);
}

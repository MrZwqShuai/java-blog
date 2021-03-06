package com.example.ngblog.service;

import com.example.ngblog.entity.Article;
import com.example.ngblog.entity.Meta;
import com.example.ngblog.entity.Result;
import com.example.ngblog.util.ResultUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by 张文强
 * 2018/2/1 9:24
 */
public interface ArticleService {

    //查询用户所有文章列表
    List<Map<String, Object>> getArticleByUser(Integer uid, Integer createDateSort);

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

    //上传图片
    Result uploadArticleImg(MultipartFile file, HttpServletRequest request);

    //通过关键字模糊查找文章
    Result getArticleListByKeywords(String keywords);
}

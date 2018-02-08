package com.example.ngblog.service;

import com.example.ngblog.entity.Article;
import com.example.ngblog.entity.Meta;
import com.example.ngblog.mapper.ArticleDao;
import com.example.ngblog.util.CrawlerUtil;
import com.example.ngblog.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by 张文强
 * 2018/2/1 9:46
 */
@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleDao articleDao;

    public List<Map<String, Object>> getArticleByUser(Integer uid) {
        return articleDao.getArticleByUser(uid);
    }

    public Integer getArticleTotalByUser(Integer id) {
        return articleDao.getArticleTotalByUser(id);
    }

    public Map<String, Object> getOneArticleDetails(Integer articleId) {
        //增加pv
        articleDao.addPageView(articleId);
        return articleDao.getOneArticleById(articleId);
    }

    public List<Map<String, Meta>> getAllMeta() {
//        ArrayList metaAll = new ArrayList();
        List<Map<String, Meta>> metaArray = articleDao.getAllMeta();
//        for(Map<String, Meta> meta: metaArray) {
//            System.out.println(meta.get("name"));
//            metaAll.add(meta.get("name"));
//        }
        return metaArray;
    }

    public List<Map<String, Article>> getArticleByMeta(Integer tagId){
        return articleDao.getArticleByMeta(tagId);
    }

    /**
     *
     * @param url 爬虫的url
     * @param target 爬虫的内容
     * @return
     * @throws IOException
     */
    public void crawlerArticle(String url, String target) throws IOException {
        Map m1 = new HashMap();
        m1.put("title", "标题");
        m1.put("content", CrawlerUtil.success(url, target));
        m1.put("create_date", new Timestamp(System.currentTimeMillis()));
        System.out.println("当前时间: " +  new Timestamp(System.currentTimeMillis()));
        articleDao.crawlerArticle(m1);
    }

    //文章添加标签
    public void postMeta(Integer articleId, Integer tagId) {
        Map m1 = new HashMap();
        m1.put("article_id", articleId);
        m1.put("tag_id", tagId);
        articleDao.postMeta(m1);
    }

    //为用户增加一片文章
    public void postOneArticleByUser(Map<String, Object> article) {
        article.put("create_date", new Timestamp(System.currentTimeMillis()));
        articleDao.postOneArticleByUser(article);
    }
}

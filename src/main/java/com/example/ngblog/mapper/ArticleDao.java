package com.example.ngblog.mapper;

import com.example.ngblog.entity.Article;
import com.example.ngblog.entity.Meta;
import com.example.ngblog.entity.TagRelationShip;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 张文强
 * 2018/1/23 15:20
 */
@Mapper
public interface ArticleDao {
    public List<Map<String, Object>> getArticleByUser(@Param("uid") Integer uid, @Param("createDateSort") Integer createDateSort);

    public Integer getArticleTotalByUser(Integer uid);

    public Map<String, Object> getOneArticleById(Integer articleId);

    public void addPageView(Integer articleId);

    public List<Map<String, Meta>> getAllMeta();

    public void crawlerArticle(Map<String,Object> param);

    public List<Map<String, Article>> getArticleByMeta(Integer tagId);

    public void postMeta(Map<String, Object> target);

    public Integer postOneArticleByUser(Article article);

    public void postMetaForArticle(TagRelationShip tagRelationShip);

    public List<Map<String, Object>> getArticleListByKeywords(@Param("keywords") String keywords);
}

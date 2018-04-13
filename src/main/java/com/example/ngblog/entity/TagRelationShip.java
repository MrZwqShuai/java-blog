package com.example.ngblog.entity;

/**
 * Created by 张文强
 * 2018/4/13 13:24
 */
public class TagRelationShip {
    private Integer id;
    private Integer article_id;
    private Integer tag_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public Integer getTag_id() {
        return tag_id;
    }

    public void setTag_id(Integer tag_id) {
        this.tag_id = tag_id;
    }
}

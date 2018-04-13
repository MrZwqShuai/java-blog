package com.example.ngblog.service;

import com.example.ngblog.entity.Article;
import com.example.ngblog.entity.Meta;
import com.example.ngblog.entity.Result;
import com.example.ngblog.mapper.ArticleDao;
import com.example.ngblog.util.CrawlerUtil;
import com.example.ngblog.util.ResultUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
        System.out.println(article);
        Integer id = articleDao.postOneArticleByUser(article);
        System.out.println(id + "000000");
        System.out.println(article.get("uid"));
    }

    int i = 0;
    //上传图片
    public Result uploadArticleImg(MultipartFile file, HttpServletRequest request) {
        Map<String, Object> finFilePath = new HashMap<String, Object>();
        if(file != null) {
            long startTime = System.currentTimeMillis();
            String filename = file.getOriginalFilename();
//            System.out.println("上传文件的名称:" + filename);
            System.out.println("上传文件的大小:" + file.getSize());
            if(file.getSize() > 1024) {
            }
//            System.out.println("上传文件的后缀名:" + FilenameUtils.getExtension(filename));
            String webapp = request.getSession().getServletContext().getRealPath("/upload");
            System.out.println("上传文件保存服务器的路径:" + webapp);

            File deskFile = new File("C://ngblog//article", i++ + filename);
            System.out.println("上传文件方的最终路径: " + deskFile.getAbsolutePath());
            finFilePath.put("url", deskFile.getAbsolutePath());

            File parentFile = deskFile.getParentFile();
            if( !parentFile.exists() ) {
                parentFile.mkdirs();
            }

            try {
                InputStream inputStream = file.getInputStream();
                FileOutputStream output = new FileOutputStream(deskFile);
                IOUtils.copy(inputStream, output);
                inputStream.close();
                output.close();
            } catch (Exception e) {
                finFilePath.put("data", null);
                return ResultUtil.error(-1, e.getMessage());
            }
        }
        return ResultUtil.success(finFilePath);
    }
}

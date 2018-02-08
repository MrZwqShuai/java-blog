package com.example.ngblog.util;

import com.sun.javafx.fxml.builder.URLBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.Document;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 张文强
 * 2018/2/3 9:25
 */

public class CrawlerUtil {

    public static String success(String url, String target) throws IOException{
        CloseableHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        org.jsoup.nodes.Document document = Jsoup.parse(EntityUtils.toString(entity), "UTF-8");
        Element title = document.select(target).first();
        System.out.println("抓取的网页: " + document);
        String html = title.html();
        response.close();
        httpClient.close();
        return html;
    }
//    public static void main(String[] args) throws IOException {
//
//        CloseableHttpClient httpClient = new DefaultHttpClient();
//        HttpGet httpGet = new HttpGet(url);
//        CloseableHttpResponse response = null;
//        response = httpClient.execute(httpGet);
//        HttpEntity entity = response.getEntity();
////        System.out.println("网页内容: " + EntityUtils.toString(entity, "utf-8"));
//        org.jsoup.nodes.Document document = Jsoup.parse(EntityUtils.toString(entity, "utf-8"));
//        Element title = document.select("div.blogpost-body").first();
//        String html = title.html();
//        System.out.println("爬虫: " + html);
//        response.close();
//        httpClient.close();
//    }
}

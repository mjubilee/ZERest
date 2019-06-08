package com.ze.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ze.news.Article;

import java.util.ArrayList;
import java.util.List;

public class DataTransformation {

    public DataTransformation() {
        super();
    }

    public static List<Article> transformJsonToArticleList(String json) {

        System.out.println("mj" + json);


        ObjectMapper mapper = new ObjectMapper();

        //Article a = mapper.readValue(br.toString(), Article.class);

        List<Article> data = new ArrayList<Article>();
        for (int i=1; i< 30; i++){
            Article a = new Article();
            a.setAuthor(i + " author");
            a.setDescription(i + " this is description");
            a.setExtracted(false);
            a.setPublishedAt(i + " month of 2019");
            a.setSource(i + " CNN");
            a.setUrl(i + " http://mahalunggu.jubilee.com");
            a.setUrlToImage(i + " http://mahalunggu.jubilee.com/image1.jpg");
            a.setTitle(i + " title");

            data.add(a);
        }

        return data;
    }
}

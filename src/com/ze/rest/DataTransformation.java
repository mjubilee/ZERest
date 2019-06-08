package com.ze.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ze.news.Article;
import com.ze.news.News;

import java.util.ArrayList;
import java.util.List;

public class DataTransformation {

    public DataTransformation() {
        super();
    }

    public static List<Article> transformJsonToArticleList(String json) throws Exception {

        News news = new ObjectMapper().readValue(json, News.class);

        if (news.getStatus() == "error") {
            return new ArrayList<Article>();
        }

        List<Article> articles = news.getArticles();

        for ( Article a : articles ) {
            a.setSource(news.getSource());
        }

        return articles;
    }
}

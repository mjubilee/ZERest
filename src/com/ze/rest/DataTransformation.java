package com.ze.rest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ze.news.Article;
import com.ze.news.News;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * DataTransform class has collection of functions that are used to transform
 * JSON formatted string into data object; therefore, the application can manipulate
 * the data further.
 *
 * @author  Mahalunggu Jubilee
 * @version 1.0
 * @since   2018-06-07
 */

public class DataTransformation {

    /**
     * transformJsonToArticleList method is used to transfer JSON formatted string into
     * list of Article object.
     *
     * @param  json a JSON formatted string
     * @return      collection of Article object
     * @see         List
     */

    public static List<Article> transformJsonToArticleList(String json) {

        List<Article> articles = null;

        try {
            News news = new ObjectMapper().readValue(json, News.class);

            if (news.getStatus() == "error") {
                return new ArrayList<>();
            }

            articles = news.getArticles();

            for (Article article : articles) {
                article.setSource(news.getSource());
            }

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return articles;
    }
}

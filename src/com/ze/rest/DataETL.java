package com.ze.rest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataETL {

    private DataSource source;

    public DataETL(){
        super();
    }

    public DataETL(DataSource s) {
        this.source = s;
    }

    public void processArticle()  {

        SimpleDateFormat sdf = new SimpleDateFormat("MMM_dd_yyyy");
        String fileName = "top_headlines_" + sdf.format(new Date()) + ".scv";

        DataExtraction dc = new DataExtraction(this.source);
        DataLoader dl = new DataLoader();

        try {
            String jsonString = dc.extractArticles();

            dl.loadArticleToFile(fileName, "", DataTransformation.transformJsonToArticleList(jsonString));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

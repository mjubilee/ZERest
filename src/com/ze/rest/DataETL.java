package com.ze.rest;

public class DataETL {

    private DataSource source;

    public DataETL(){
        super();
    }

    public DataETL(DataSource s) {
        this.source = s;
    }

    public void processArticle()  {
        try {
            DataExtraction dc = new DataExtraction(this.source);
            DataLoader dl = new DataLoader();

            String jsonString = dc.extractArticles();

            String fileName = "top_headlines_June_05_2019.csv";
            dl.loadArticleToFile(fileName, "", DataTransformation.transformJsonToArticleList(jsonString));



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

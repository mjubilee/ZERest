package com.ze.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ze.news.Article;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class DataCollection {
    private DataSource source;

    public DataCollection(){
        super();
    }

    public DataCollection(DataSource s) {
        this.source = s;
    }

    public DataSource getSource() {
        return source;
    }

    public void setSource(DataSource source) {
        this.source = source;
    }

    public List<Article> retrieveArticles() {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            CloseableHttpResponse response = establishConnection(httpclient);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

            String output;
            output = br.readLine();
           // while ((output = br.readLine()) != null) {

           // }



            ObjectMapper mapper = new ObjectMapper();

//
                //Article a = mapper.readValue(br.toString(), Article.class);

            List<Article> data = new ArrayList<Article>();
            for (int i=1; i< 20; i++){
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

            String fileName = "top_headlines_June_05_2019.csv";
            DataLoader de = new DataLoader();
            de.loadArticleToFile(fileName, "", data);




            httpclient.close();

            System.out.println("mj" + output);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<Article>();
    }

    private CloseableHttpResponse establishConnection(CloseableHttpClient httpclient ) throws Exception {

        URI uri = new URIBuilder()
                .setScheme( source.getScheme() )
                .setHost( source.getHost() )
                .setPath( source.getPath() )
                .setParameters( source.getParameters() )
                .build();

        HttpGet httpget = new HttpGet(uri);
        httpget.setHeader("Content-type", "application/json");

        httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = httpclient.execute(httpget);

        return response;

    }
}

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
            while ((output = br.readLine()) != null) {
    //            System.out.println("mj" + output);
            }






            ObjectMapper mapper = new ObjectMapper();

//
                //Article a = mapper.readValue(br.toString(), Article.class);



















            httpclient.close();

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

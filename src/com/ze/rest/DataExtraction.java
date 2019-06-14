package com.ze.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * DataExtraction class has collection of functions that are used consume REST API
 * from specific resource and then produce JSON formatted string for further data processing
 *
 * @author  Mahalunggu Jubilee
 * @version 1.0
 * @since   2018-06-07
 */

public class DataExtraction {
    private DataSource source;


    public DataExtraction(DataSource dataSource) {
        this.source = dataSource;
    }

    /**
     * extractArticles method is used to collect the data from REST API and then keep them
     * in the JSON formatted string
     *
     * @return      JSON formatted string
     * @see         String
     */

    public String extractArticles() {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = establishConnection(httpclient);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        }

        String output = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
            output = br.readLine();

            httpclient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output;
    }

    /**
     * establishConnection method is used to create a connection with data source and to request
     * a JSON formatted information as the response
     *
     * @return  CloseableHttpResponse   HTTP response
     * @see                             CloseableHttpResponse
     */

    private CloseableHttpResponse establishConnection(CloseableHttpClient httpclient ) {

        CloseableHttpResponse response = null;

        try {
            URI uri = new URIBuilder()
                    .setScheme(source.getScheme())
                    .setHost(source.getHost())
                    .setPath(source.getPath())
                    .setParameters(source.getParameters())
                    .build();

            HttpGet httpget = new HttpGet(uri);
            httpget.setHeader("Content-type", "application/json");

            httpclient = HttpClients.createDefault();
            response = httpclient.execute(httpget);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch(ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;

    }

    public String extractDataUsingJersey() {

        String output = null;

        try {
            Client client = Client.create();
            URI uri = new URIBuilder()
                    .setScheme(source.getScheme())
                    .setHost(source.getHost())
                    .setPath(source.getPath())
                    .setParameters(source.getParameters())
                    .build();

            WebResource webResource = client.resource(uri);
            ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }

            output = response.getEntity(String.class);
            response.close();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return output;
    }
}

package com.ze.rest;

import org.apache.http.NameValuePair;

import java.util.List;

/**
 * DataSource class is data object class that contain the REST API source, input parameters
 * to retrieve the REST API.
 *
 * @author  Mahalunggu Jubilee
 * @version 1.0
 * @since   2018-06-07
 */

public class DataSource {
    private String scheme;
    private String host;
    private String path;
    private List<NameValuePair> parameters;


    public DataSource(){
    }

    public DataSource(String scheme,
            String host,
            String path,
            List<NameValuePair> parameters) {
        this.scheme = scheme;
        this.host = host;
        this.path = path;
        this.parameters = parameters;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<NameValuePair> getParameters() {
        return parameters;
    }

    public void setParameters(List<NameValuePair> parameters) {
        this.parameters = parameters;
    }
}

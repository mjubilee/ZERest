package com.ze.rest;

import org.apache.http.NameValuePair;

import java.util.List;

public class DataSource {
    private String scheme;
    private String host;
    private String path;
    private List<NameValuePair> parameters;


    public DataSource(){
        super();
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
package com.ze.util;

import com.ze.rest.DataSource;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class Configuration {
    private String scheme;
    private String host;
    private String path;
    private List<Parameter> parameters;

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

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public DataSource createDataSource() {
        List<NameValuePair> param = new ArrayList<>();
        for ( Parameter p : this.parameters) {
            param.add( new BasicNameValuePair(p.getKey(), p.getValue()) );
        }

        return new DataSource(this.scheme, this.host, this.path, param);
    }

}

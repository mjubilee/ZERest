package com.ze.util;

import com.ze.rest.DataSource;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Configuration class is data object class that keep all of application configuration
 * information from user input. The information contain the REST API source, input parameters
 * to retrieve the REST API, csv output file name, delimiter character in the output file,
 * test file name, and test mode.
 *
 * @author  Mahalunggu Jubilee
 * @version 1.0
 * @since   2018-06-07
 */

public class Configuration {
    private String scheme;
    private String host;
    private String path;
    private List<Parameter> parameters;
    private String delimiter;
    private String outputFileName;
    private String testMode;
    private String testFile;

    /**
     * This method is used to generate DataSource object.
     * DataSource object required list of NameValuePair as parameter;
     * therefore, a process to construct the list form Parameter object collection
     * is required.
     *
     * @return      DataSource object
     * @see         List
     */

    public DataSource createDataSource() {
        List<NameValuePair> parameterList = new ArrayList<>();
        for ( Parameter parameter : this.parameters) {
            parameterList.add( new BasicNameValuePair(parameter.getKey(), parameter.getValue()) );
        }

        return new DataSource(this.scheme, this.host, this.path, parameterList);
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

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getTestMode() {
        return testMode;
    }

    public void setTestMode(String testMode) {
        this.testMode = testMode;
    }

    public String getTestFile() {
        return testFile;
    }

    public void setTestFile(String testFile) {
        this.testFile = testFile;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }
}

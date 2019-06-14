package com.ze.rest;

import com.ze.util.Configuration;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DataEtl class is the main class to Extract, Transform, and Load the information
 * from REST API to csv file.
 * The class has a constructor that has an input parameter, Configuration object
 *
 * @author  Mahalunggu Jubilee
 * @version 1.0
 * @since   2018-06-07
 */

public class DataEtl {

    private static final String FILE_EXT = ".csv";
    private static final String DATE_FORMAT = "MMM_dd_yyyy";

    private DataSource source;
    private Configuration configuration;

    public DataEtl() {
    }

    public DataEtl(Configuration conf) {
        this.configuration = conf;
        this.source = this.configuration.createDataSource();
    }

    /**
     * processArticle method is the main to run the ETL processes by calling DataLoader class,
     * DataExtraction class, and DataTransformation class.
     *
     * @see         String
     */

    public void processArticle()  {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String fileName = configuration.getOutputFileName() + dateFormat.format(new Date()) + FILE_EXT;

        DataExtraction dataExtraction = new DataExtraction(this.source);
        DataLoader dataLoader = new DataLoader();

        String jsonString = "";

        if (configuration.getTestMode().equals("false")) {
//            jsonString = dataExtraction.extractArticles();
            jsonString = dataExtraction.extractDataUsingJersey();
        } else {
            jsonString = retrieveTestData();
        }

        dataLoader.loadArticleToFile(fileName
                , configuration.getDelimiter()
                , DataTransformation.transformJsonToArticleList(jsonString));

    }

    /**
     * retrieveTestData method is used to create a test JSON formatted
     * string in order to test the application locally
     *
     * @return      a JSON formatted string
     * @see         String
     */

    private String retrieveTestData() {
        String jsonString = "";

        try {
            JSONParser parser = new JSONParser();
            BufferedReader reader = new BufferedReader(new FileReader(configuration.getTestFile()));

            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            jsonString = jsonObject.toJSONString();

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return jsonString;
    }
}

package com.ze.rest;

import com.ze.news.Article;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * DataLoader class has collection of functions that are used to load the data object into
 * CSV file.
 *
 * @author  Mahalunggu Jubilee
 * @version 1.0
 * @since   2018-06-07
 */


public class DataLoader {

    public DataLoader(){
    }


    /**
     * loadDataToFile method is used to create a file and save the data from List
     * This method will create a file if it does not exist and it will append the data if the file exist
     *
     * @param  fileName  a string contain file name
     * @param  delimiter a string that separate the information in the file
     * @param  data      a list of List String
     * @see              List
     */

    private void loadDataToFile(String fileName, String delimiter, List<List<String>> data) {

        try {
            FileWriter file = new FileWriter(fileName, true);

            for (List<String> rowData : data) {
                file.append(String.join(delimiter, rowData));
                file.append("\n");
            }

            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * retrieveDataFromFile method is used to retrieve information from a file.
     * This method will create a list of information and keep it in a hash map
     *
     * @param  fileName  a string contain file name
     * @param  delimiter a string that separate the information in the file
     * @see              HashMap
     */

    private HashMap<String, List<String>> retrieveDataFromFile(String fileName, String delimiter) {

        HashMap<String, List<String>> data = new HashMap<>();
        File file = new File(fileName);

        try {
            if (file.isFile()) {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                String row;

                while ((row = reader.readLine()) != null) {
                    String[] dataRow = row.split(delimiter);
                    List<String> list = Arrays.asList(dataRow);

                    //this condition to prevent the index out of bound on the last line
                    if (dataRow.length > 3 ) {
                        data.put(dataRow[3], list);
                    }
                }

                reader.close();
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return data;

    }

    /**
     * loadArticleToFile method is used to extract an Article object to a file.
     * This method will filter the Article object whether the data exist in file or not
     * If the data exist in file, the isExtracted property will be set to true
     *
     * @param  fileName a string contain file name
     * @param  data     a list of Article object
     * @see             List
     */

    public void loadArticleToFile(String fileName, String delimiter, List<Article> data) {

        HashMap<String, List<String>> file = retrieveDataFromFile(fileName, delimiter);

        for (int i = 0; i < data.size(); i++) {
            if (file.containsKey(data.get(i).getUrl())) {
                data.get(i).setExtracted(true);
            }
        }

        loadDataToFile(fileName, delimiter, loadArticleToList(data));

    }

    /**
     * loadArticleToList method is used to transform an Article object to a list of string.
     * This method always returns a list of String list, whether or not the
     * list is empty.
     *
     * @param  data a list of Article object
     * @return      collection of String List
     * @see         List
     */

    private List<List<String>> loadArticleToList(List<Article> data) {
        List<List<String>> articleList = new ArrayList<>();

        for (Article article : data) {
            if ( !article.isExtracted() ) {
                articleList.add(Arrays.asList(article.getSource()
                        , article.getAuthor()
                        , article.getTitle()
                        , article.getUrl()
                        , article.getPublishedAt()));
            }
        }

        return articleList;
    }
}

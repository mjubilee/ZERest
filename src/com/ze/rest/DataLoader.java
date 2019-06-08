package com.ze.rest;

import com.ze.news.Article;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DataLoader {

    public DataLoader(){
        super();
    }


    /**
     * This method is used to creat a file and save the data from List
     * This method will create a file if it does not exist and it will append the data if the file exist
     *
     * @param  fileName  a string contain file name
     * @param  path      a string contain directory path
     * @param  delimiter a string that separate the information in the file
     * @param  data      a list of List String
     * @see              List
     */

    private void loadDataToFile(String fileName, String path, String delimiter, List<List<String>> data) throws Exception {
        FileWriter file = new FileWriter(path + fileName, true);


        for (List<String> rowData : data) {
            file.append(String.join(delimiter, rowData));
            file.append("\n");
        }

        file.flush();
        file.close();
    }

//    private List<List<String>> retrieveDataFromFile(String fileName, String path, String delimiter) throws Exception {
//
//        List<List<String>> data = new ArrayList<List<String>>();
//
//        File file = new File(path + fileName);
//
//        if (file.isFile()) {
//            BufferedReader reader = new BufferedReader(new FileReader(path + fileName));
//            String row;
//
//            while ((row = reader.readLine()) != null) {
//                String[] dataRow = row.split(delimiter);
//                List<String> list= Arrays.asList(dataRow);
//                data.add(list);
//            }
//
//            reader.close();
//        }
//
//        return data;
//
//    }

    /**
     * This method is used to retrieve information from a file.
     * This method will create a list of information and keep it in a hash map
     *
     * @param  fileName  a string contain file name
     * @param  path      a string contain directory path
     * @param  delimiter a string that separate the information in the file
     * @see              HashMap
     */

    private HashMap<String, List<String>> retrieveDataFromFile(String fileName, String path, String delimiter) throws Exception {

        HashMap<String, List<String>> data = new HashMap<String, List<String>>();

        File file = new File(path + fileName);

        if (file.isFile()) {
            BufferedReader reader = new BufferedReader(new FileReader(path + fileName));
            String row;

            while ((row = reader.readLine()) != null) {
                String[] dataRow = row.split(delimiter);
                List<String> list= Arrays.asList(dataRow);
                data.put(dataRow[3], list);
            }

            reader.close();
        }

        return data;

    }

    /**
     * This method is used to extract an Article object to a file.
     * This method will filter the Article object whether the data exist in file or not
     * If the data exist in file, the isExtracted property will be set to true
     *
     * @param  fileName a string contain file name
     * @param  path     a string contain directory path
     * @param  data     a list of Article object
     * @see             List
     */

    public void loadArticleToFile(String fileName, String path, List<Article> data) throws Exception {

        HashMap<String, List<String>> file = retrieveDataFromFile(fileName, path, "~~");

        for (int i = 0; i < data.size(); i++ ) {
            if ( file.containsKey(  data.get(i).getUrl() ) ) {
                data.get(i).setExtracted(true);
            }
        }


        loadDataToFile(fileName, path, "~~", loadArticleToList(data));

    }

    /**
     * This method is used to transform an Article object to a list of string.
     * This method always returns a list of String list, whether or not the
     * list is empty.
     *
     * @param  data a list of Article object
     * @return      collection of String List
     * @see         List
     */

    private List<List<String>> loadArticleToList(List<Article> data) {
        List<List<String>> result = new ArrayList<List<String>>();

        for (Article a : data) {
            if ( !a.isExtracted() ) {
                result.add(Arrays.asList(a.getSource(), a.getAuthor(), a.getTitle(), a.getUrl(), a.getPublishedAt()));
            }
        }

        return result;
    }
}

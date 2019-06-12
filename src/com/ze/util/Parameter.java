package com.ze.util;

/**
 * Parameter class is data object class that keep key and value pair.
 * This class is used to keep the input parameter from configuration file
 *
 * @author  Mahalunggu Jubilee
 * @version 1.0
 * @since   2018-06-07
 */

public class Parameter {
    private String key;
    private String value;

    public Parameter(){}

    public Parameter(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

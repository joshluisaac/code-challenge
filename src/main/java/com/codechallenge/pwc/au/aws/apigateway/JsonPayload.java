package com.codechallenge.pwc.au.aws.apigateway;

/**
 * JSON payload object
 *
 * @author Joshua Nwankwo
 * @version 1.0
 * @since March 2019
 */


public class JsonPayload {

    public String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonPayload{" +
                "data='" + data + '\'' +
                '}';
    }
}

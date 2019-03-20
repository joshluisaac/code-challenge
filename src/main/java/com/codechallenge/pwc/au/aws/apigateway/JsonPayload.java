package com.codechallenge.pwc.au.aws.apigateway;

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

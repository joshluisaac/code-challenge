package com.codechallenge.pwc.au.aws.apigateway;

import com.codechallenge.pwc.au.entities.Contact;

public class LambdaResponse {

    private int httpStatusCode;
    private String responseBody;


    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }


    @Override
    public String toString() {
        return "LambdaResponse{" +
                "httpStatusCode=" + httpStatusCode +
                ", responseBody='" + responseBody + '\'' +
                '}';
    }
}

package com.codechallenge.pwc.au.aws.apigateway;

/**
 * Response class for an API Gateway lambda
 *
 * @author Joshua Nwankwo
 * @version 1.0
 * @since March 2019
 */

public class ApiResponse {

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

package com.codechallenge.pwc.au.aws.lambda;

import com.codechallenge.pwc.au.entities.Contact;

public class LambdaRequest {

    String name;
    private String httpMethod;
    String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

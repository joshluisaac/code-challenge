package com.codechallenge.pwc.au.aws.apigateway;

import com.codechallenge.pwc.au.entities.Contact;

public class LambdaResponse {

    private String httpMethod;
    private Contact contact;


    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }




}

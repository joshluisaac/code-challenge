package com.codechallenge.pwc.au.aws.apigateway;

import com.codechallenge.pwc.au.entities.Contact;

public class LambdaRequest {

    public String name;
    public String httpMethod;
    public String body;
    public String phoneNumber;
    public String bodyJson;
    public Contact contact;
    public JsonPayload jsonPayload;

    public JsonPayload getJsonPayload() {
        return jsonPayload;
    }

    public void setJsonPayload(JsonPayload jsonPayload) {
        this.jsonPayload = jsonPayload;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBodyJson() {
        return bodyJson;
    }

    public void setBodyJson(String bodyJson) {
        this.bodyJson = bodyJson;
    }

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

    @Override
    public String toString() {
        return "LambdaRequest{" +
                "name='" + name + '\'' +
                ", httpMethod='" + httpMethod + '\'' +
                ", body='" + body + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", bodyJson='" + bodyJson + '\'' +
                '}';
    }
}

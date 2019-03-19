package com.codechallenge.pwc.au.aws.lambda;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.codechallenge.pwc.au.entities.Contact;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class LambdaRequestHandler implements RequestStreamHandler, RequestHandler<LambdaRequest, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest request, Context context) {
        context.getLogger().log("Handling request");

        context.getLogger().log("Request name: " + request.getHttpMethod());

        context.getLogger().log("Body: " + request.getBody());

        context.getLogger().log("Body: " + request.getName());

        LambdaResponse response = new LambdaResponse();
        response.setContact(processRequest(request));


        return response;
    }


    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {

    }






    public Contact processRequest(LambdaRequest request){
        SortedMap<String, Contact> contacts = new TreeMap<>();
        contacts.put("Joshua",new Contact("Joshua","3788910"));
        contacts.put("zoe",new Contact("zoe","9878676"));
        contacts.put("lucas",new Contact("lucas","8866666"));
        contacts.put("Asha",new Contact("Asha","78884340"));
        return contacts.get(request.name);

    }


    void handlePost(){

    }

    void handleGet(){

    }
}

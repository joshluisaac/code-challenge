package com.codechallenge.pwc.au.aws;


//com.codechallenge.pwc.au.aws.ApiGatewayAddressBookMicroService::handleRequest



import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.codechallenge.pwc.au.AddressBookAppCLI;
import com.codechallenge.pwc.au.aws.apigateway.JsonPayload;
import com.codechallenge.pwc.au.aws.apigateway.LambdaRequest;
import com.codechallenge.pwc.au.aws.apigateway.LambdaResponse;
import com.codechallenge.pwc.au.components.InputValidationParser;
import com.codechallenge.pwc.au.entities.AddressBook;
import com.codechallenge.pwc.au.entities.Contact;
import com.codechallenge.pwc.au.persistence.AddressBookDao;
import com.codechallenge.pwc.au.services.AddressBookService;
import com.codechallenge.pwc.au.services.AddressBookUnionService;
import com.codechallenge.pwc.au.services.IAddressBookUnionService;
import com.codechallenge.pwc.au.utils.MapperUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.SortedMap;
import java.util.TreeMap;

public class ApiGatewayAddressBookMicroService implements RequestHandler<LambdaRequest, String> {

    @Override
    public String handleRequest(LambdaRequest request, Context context) {

        final String httpMethod =  request.getHttpMethod();
        LambdaResponse response = new LambdaResponse();

        if (httpMethod.equals("GET")) {
            context.getLogger().log("Processing get request");
            readFile(getS3Client(),context);
            replaceFile(getS3Client(),context);
        } else if (httpMethod.equals("POST")) {
            context.getLogger().log("Handling post request");

            //fetch JSON payload
            JsonPayload jsonPayload = request.getJsonPayload();

            //extract data from payload
            String requestData = request.getJsonPayload().getData();

            //validate payload
            boolean isValidJson = InputValidationParser.isValidJson(requestData);

            if (isValidJson) {
                handlePost(requestData);
            } else {
                final String errMsg = MessageFormat.format("{0} is not a valid JSON formatted string.", requestData);
                context.getLogger().log(errMsg);
            }

        }


        return request.toString();
    }




    public void handlePost(final String postData){

        try {
            AddressBookService service = new AddressBookService(new AddressBookDao(), new AddressBook());
            IAddressBookUnionService unionService = new AddressBookUnionService();
            AddressBookAppCLI cli = new AddressBookAppCLI(service, unionService);
            SortedMap<String, String> union = cli.executeUnion(cli.getAddressBook(), MapperUtils.remapBook2(postData));
            System.out.println("Book 1/Book 2: " + union.toString());

        } catch (IOException ex) {
            System.out.println("Error occurred when parsing JSON payload");
        }



    }

    void handleGet(){

    }


    public AmazonS3 getS3Client(){
        return AmazonS3ClientBuilder.standard().withRegion(Constants.REGION).build();
    }


    void readFile(AmazonS3 s3Client, Context ctx){

        S3Object fullObject = null;

        System.out.println("Downloading an object");
        fullObject = s3Client.getObject(new GetObjectRequest(Constants.BUCKET_NAME, Constants.KEY));
        System.out.println("Content-Type: " + fullObject.getObjectMetadata().getContentType());
        System.out.println("Content: ");
        InputStream input = fullObject.getObjectContent();

        try{
            System.out.println(IOUtils.toString(input, StandardCharsets.UTF_8));
            ctx.getLogger().log(IOUtils.toString(input, StandardCharsets.UTF_8));
        } catch (IOException ex) {
            ctx.getLogger().log("Error logged");
            System.out.println("Error occurred");

        }




    }


    void replaceFile(AmazonS3 s3Client, Context ctx){
        s3Client.putObject(Constants.BUCKET_NAME,Constants.KEY,"{Josh:5667778,Ada:5678788}");

    }



}

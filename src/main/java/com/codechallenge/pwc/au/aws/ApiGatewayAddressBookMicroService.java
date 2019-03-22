package com.codechallenge.pwc.au.aws;


/**
 * An implementation of {@link RequestHandler} that handles POST and GET requests.
 *
 * @author Joshua Nwankwo
 * @version 1.0
 * @since March 2019
 */


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.codechallenge.pwc.au.AddressBookAppCLI;
import com.codechallenge.pwc.au.aws.apigateway.ApiRequest;
import com.codechallenge.pwc.au.aws.apigateway.ApiResponse;
import com.codechallenge.pwc.au.aws.apigateway.JsonPayload;
import com.codechallenge.pwc.au.components.InputValidationParser;
import com.codechallenge.pwc.au.components.S3ReadObjectStreamStrategy;
import com.codechallenge.pwc.au.entities.AddressBook;
import com.codechallenge.pwc.au.entities.Contact;
import com.codechallenge.pwc.au.persistence.AddressBookDao;
import com.codechallenge.pwc.au.services.AddressBookService;
import com.codechallenge.pwc.au.services.AddressBookUnionService;
import com.codechallenge.pwc.au.services.IAddressBookUnionService;
import com.codechallenge.pwc.au.utils.JsonUtils;
import com.codechallenge.pwc.au.utils.MapperUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Optional;
import java.util.SortedMap;

public class ApiGatewayAddressBookMicroService implements RequestHandler<ApiRequest, String> {

    private static final Logger LOG = LoggerFactory.getLogger(ApiGatewayAddressBookMicroService.class);

    public ApiGatewayAddressBookMicroService(){

    }


    @Override
    public String handleRequest(ApiRequest request, Context context) {
        final String httpMethod = request.getHttpMethod();
        final ApiResponse response = new ApiResponse();
        if (httpMethod.equals("GET")) {
            LOG.info("PWC_API_GW_INFO: Handling GET request");
            handleGet(request);
            final String responseBody = fetchObjectContents(getS3Client());
            response.setResponseBody(responseBody);
            response.setHttpStatusCode(HttpStatus.SC_OK);
        } else if (httpMethod.equals("POST")) {
            LOG.info("PWC_API_GW_INFO: Handling POST request");

            //fetch JSON payload
            JsonPayload jsonPayload = request.getJsonPayload();

            //extract data from payload
            String requestData = request.getJsonPayload().getData();

            //validate payload
            boolean isValidJson = InputValidationParser.isValidJson(requestData);

            if (isValidJson) {
                final String responseBody = handlePost(requestData);
                response.setResponseBody(responseBody);
                response.setHttpStatusCode(HttpStatus.SC_OK);
            } else {
                final String errMsg = MessageFormat.format("{0} is not a valid JSON formatted string.", requestData);
                context.getLogger().log(errMsg);
                LOG.error("PWC_API_GW_ERROR: {} is not a valid JSON", requestData);
            }
        }
        return response.toString();
    }


    public AddressBookAppCLI getAppCliInstance(){
        AddressBookService service = new AddressBookService(new AddressBookDao(), new AddressBook(new S3ReadObjectStreamStrategy(readObject(getS3Client()))));
        IAddressBookUnionService unionService = new AddressBookUnionService();
         return new AddressBookAppCLI(service, unionService);
    }


    public String handlePost(final String postData) {
        final AddressBookAppCLI cli = getAppCliInstance();
        SortedMap<String, String> union = cli.executeUnion(cli.getAddressBook(), MapperUtils.remapBook2(postData));
        String result = union.toString();
        System.out.println("Book 1/Book 2: " + result);
        return result;
    }

    public void handleGet(ApiRequest request) {
        Optional<Contact> maybeContact = Optional.of(new Contact(request.getName().trim(), request.getPhoneNumber().trim()));
        Contact contact = maybeContact.orElse(new Contact());
        final AddressBookAppCLI cli = getAppCliInstance();
        cli.execute(contact);
        cli.displayAddressBook();
        replaceObject(getS3Client(),new JsonUtils().toJson(cli.getAddressBook()));
    }


    public AmazonS3 getS3Client() {
        return AmazonS3ClientBuilder.standard().withRegion(Constants.REGION).build();
    }


    public String fetchObjectContents(AmazonS3 s3Client) {
        try {
            return IOUtils.toString(readObject(s3Client), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            LOG.error("Error occurred");
        }
        return null;
    }


    public InputStream readObject(AmazonS3 s3Client) {
        S3Object fullObject = null;
        fullObject = s3Client.getObject(new GetObjectRequest(Constants.BUCKET_NAME, Constants.KEY));
        return fullObject.getObjectContent();
    }


    public void replaceObject(AmazonS3 s3Client, final String textContent){
        s3Client.putObject(Constants.BUCKET_NAME,Constants.KEY,textContent);
    }


}

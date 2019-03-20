package com.codechallenge.pwc.au.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class ApiGatewayS3ClientProvider {

    public static AmazonS3 getS3Client(){
        return AmazonS3ClientBuilder.standard().withRegion(Constants.REGION).build();
    }

}

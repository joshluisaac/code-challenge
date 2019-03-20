package com.codechallenge.pwc.au.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * AWS S3 client provider object.
 *
 * @author Joshua Nwankwo
 * @version 1.0
 * @since March 2019
 */

public class ApiGatewayS3ClientProvider {

    public static AmazonS3 getS3Client(){
        return AmazonS3ClientBuilder.standard().withRegion(Constants.REGION).build();
    }

}

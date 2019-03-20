package com.codechallenge.pwc.au;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class S3Sample {


    void readFile(){
        String clientRegion = "us-east-1";
        String bucketName = "pwc-coding-challenge-transfer";
        String key = "book.json";

        S3Object fullObject = null, objectPortion = null, headerOverrideObject = null;
        //AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new ProfileCredentialsProvider()).build();
        AmazonS3 s3Client = new AmazonS3Client();
        System.out.println("Downloading an object");
        fullObject = s3Client.getObject(new GetObjectRequest(bucketName, key));
        System.out.println("Content-Type: " + fullObject.getObjectMetadata().getContentType());
        System.out.println("Content: ");
        InputStream input = fullObject.getObjectContent();

        try{
            System.out.println(IOUtils.toString(input, StandardCharsets.UTF_8));

        } catch (IOException ex) {
            System.out.println("Error occurred");

        }




    }


    public static void main(String[] args) {
        new S3Sample().readFile();

    }
}

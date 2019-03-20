package com.codechallenge.pwc.au.components;

import java.io.InputStream;

public class S3ReadObjectStreamStrategy implements StreamStrategy {

    private InputStream inputStream;

    public S3ReadObjectStreamStrategy(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public InputStream getInputStream() {
        return inputStream;
    }

}

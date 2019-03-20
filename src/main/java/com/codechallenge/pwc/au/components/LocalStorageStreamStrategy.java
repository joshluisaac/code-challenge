package com.codechallenge.pwc.au.components;


import java.io.InputStream;

public class LocalStorageStreamStrategy implements StreamStrategy {

    private InputStream inputStream;

    public LocalStorageStreamStrategy(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public InputStream getInputStream() {
        return inputStream;

    }

}

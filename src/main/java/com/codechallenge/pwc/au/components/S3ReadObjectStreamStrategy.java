package com.codechallenge.pwc.au.components;

import java.io.InputStream;


/**
 * An implementation of {@link StreamStrategy} that returns the associated stream from AWS S3
 *
 * @author Joshua Nwankwo
 * @version 1.0
 * @since March 2019
 */

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

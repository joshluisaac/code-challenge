package com.codechallenge.pwc.au.components;


import java.io.InputStream;



/**
 * An implementation of {@link StreamStrategy} that returns the associated stream from local disk storage.
 *
 * @author Joshua Nwankwo
 * @version 1.0
 * @since March 2019
 */

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

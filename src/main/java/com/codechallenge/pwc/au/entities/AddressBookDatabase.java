package com.codechallenge.pwc.au.entities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddressBookDatabase {

    public static final String DATABASE = "book.json";



    public InputStream getDatabaseContents(File file) throws FileNotFoundException {
        return new FileInputStream(file);
    }


}

package com.codechallenge.pwc.au.persistence;

import com.codechallenge.pwc.au.entities.Contact;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface IDao {

    void update(final Map<String,String> map, final Contact contact);
    void add(final Map<String,String> map, final Contact contact);
    void writeToDataStore(final File file, final String content) throws IOException;

}

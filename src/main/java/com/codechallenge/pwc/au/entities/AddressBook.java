package com.codechallenge.pwc.au.entities;

import com.codechallenge.pwc.au.components.StreamStrategy;
import com.codechallenge.pwc.au.utils.JsonUtils;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


/**
 * A POJO object representing the address book.
 *
 * @author Joshua Nwankwo
 * @version 1.0
 * @since March 2019
 */

public class AddressBook {

    //public static final String DATABASE = "book.json";
    public Map<String,String> cache;
    public StreamStrategy streamStrategy;

    public AddressBook(StreamStrategy streamStrategy) {
        this.streamStrategy = streamStrategy;
        this.cache = readToCache();
    }

    public Map<String,String> getContacts() throws IOException {
        return cache;
    }

    public void setCache(Map<String, String> cache) {
        this.cache = cache;
    }


    /*Deserialize the JSON file into Java objects*/
    private Map<String, String> readToCache() {
        SortedMap<String, String> database = new JsonUtils().fromJson(new InputStreamReader(streamStrategy.getInputStream(), StandardCharsets.UTF_8), new TypeToken<SortedMap<String, String>>() {
        }.getType());
        SortedMap<String, String> existingContacts = new TreeMap<>(String::compareToIgnoreCase);
        for (SortedMap.Entry<String, String> entry : database.entrySet())
            existingContacts.put(entry.getKey(), entry.getValue());
        return existingContacts;
    }

    public InputStream getDatabaseContents(File file) throws FileNotFoundException{
        return new FileInputStream(file);
    }


}

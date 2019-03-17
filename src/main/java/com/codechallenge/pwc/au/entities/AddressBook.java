package com.codechallenge.pwc.au.entities;

import com.codechallenge.pwc.au.utils.JsonUtils;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class AddressBook {

    public static final String ADDRESS_BOOK_DATA_BASE = "book.json";

    public Map<String,String> cache;

    public AddressBook() throws IOException{
        this.cache = readToCache();
    }

    public Map<String,String> getContacts() throws IOException {
        return cache;
    }

    public String getBookName(){
        return ADDRESS_BOOK_DATA_BASE;
    }


    /*Deserialize the JSON file into Java objects*/
    private Map<String, String> readToCache() throws IOException {
        SortedMap<String, String> database = new JsonUtils().fromJson(new FileReader(new File(ADDRESS_BOOK_DATA_BASE)), new TypeToken<SortedMap<String, String>>() {
        }.getType());
        SortedMap<String, String> existingContacts = new TreeMap<>(String::compareToIgnoreCase);
        for (SortedMap.Entry<String, String> entry : database.entrySet())
            existingContacts.put(entry.getKey(), entry.getValue());
        return existingContacts;
    }


}

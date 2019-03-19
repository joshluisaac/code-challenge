package com.codechallenge.pwc.au.utils;

import com.google.gson.reflect.TypeToken;

import java.util.SortedMap;
import java.util.TreeMap;

public class MapperUtils {


    public static SortedMap<String, String> remapBook2(final String rawJson){
        SortedMap<String, String> book2 = new JsonUtils().fromJson(rawJson,
                new TypeToken<SortedMap<String, String>>() {
                }.getType());
        SortedMap<String, String> newBook2 = new TreeMap<>(String::compareToIgnoreCase);
        for (SortedMap.Entry<String, String> entry : book2.entrySet())
            newBook2.put(entry.getKey(), entry.getValue());
        return newBook2;
    }

}

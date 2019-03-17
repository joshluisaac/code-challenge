package com.codechallenge.pwc.au.utils;

import com.google.gson.Gson;

import java.io.Reader;
import java.lang.reflect.Type;

/**
 * A {@link Gson} wrapper class providing forwarding and helper methods for JSON utilities.
 *
 * @author Joshua Nwankwo
 * @version 1.0
 * @since March 2019
 */
public class JsonUtils {

    private Gson gson;

    public JsonUtils() {
        this.gson = new Gson();
    }


    public <T> String toJson(T prop) {
        return gson.toJson(prop);
    }


    public <T> T fromJson(String jsonText, Class<T> t) {
        return gson.fromJson(jsonText, t);
    }


    public <T> T fromJson(Reader reader, Class<T> t) {
        return gson.fromJson(reader, t);
    }


    public <T> T fromJson(Reader reader, Type t) {
        return gson.fromJson(reader, t);
    }

    public <T> T fromJson(String jsonText, Type t) {
        return gson.fromJson(jsonText, t);
    }


}

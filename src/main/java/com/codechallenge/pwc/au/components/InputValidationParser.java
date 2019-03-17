package com.codechallenge.pwc.au.components;

import com.codechallenge.pwc.au.utils.JsonUtils;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.SortedMap;

public class InputValidationParser {

    private static final Logger LOG = LoggerFactory.getLogger(InputValidationParser.class);

    public static boolean isValidJson(final String maybeJson) {
        boolean isValid = false;
        try {
            new JsonUtils().fromJson(maybeJson, new TypeToken<SortedMap<String, String>>() {
            }.getType());
            isValid = true;
        } catch (JsonSyntaxException ex) {
            LOG.error("{} is not a valid JSON", maybeJson);

        }
        return isValid;
    }


}

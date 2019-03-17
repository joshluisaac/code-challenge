package com.codechallenge.pwc.au.components;

import com.codechallenge.pwc.au.utils.JsonUtils;
import com.codechallenge.pwc.au.utils.RegexUtils;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.SortedMap;

/**
 * A utility providing command line/terminal input validation helper methods.
 *
 * @author Joshua Nwankwo
 * @version 1.0
 * @since March 2019
 */
public class InputValidationParser {

    private static final Logger LOG = LoggerFactory.getLogger(InputValidationParser.class);

    /**
     * Parses a JSON string and checks if it is really a valid JSON formatted string.
     *
     * @param maybeJson input string which was passed in from terminal
     * @return true if it is valid or false if otherwise
     */
    public static boolean isValidJson(final String maybeJson) {
        boolean isValid = false;
        try {
            new JsonUtils().fromJson(maybeJson, new TypeToken<SortedMap<String, String>>() {
            }.getType());
            isValid = true;
        } catch (JsonSyntaxException ex) {
            LOG.error("{} is not a valid JSON formatted string.", maybeJson);

        }
        return isValid;
    }

    /**
     * Parses a phone number and checks if it is a valid.
     *
     * @param maybePhoneNo input string which was passed in from terminal
     * @return true if it is valid or false if otherwise
     */
    public static boolean isValidPhoneNumber(final String maybePhoneNo) {
        return RegexUtils.hasMatch(maybePhoneNo, "^[0-9]+$");
    }

    public static void main(String[] args) {
        System.out.println(InputValidationParser.isValidPhoneNumber("907y65"));
    }


}

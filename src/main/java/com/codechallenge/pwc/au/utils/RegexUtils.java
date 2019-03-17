package com.codechallenge.pwc.au.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A utility providing regex helper methods.
 *
 * @author Joshua Nwankwo
 * @version 1.0
 * @since March 2019
 */

public class RegexUtils {

    /**
     * Checks if the input provided matches the given pattern.
     * @param candidate input string which needs to be checked
     * @param pattern the given pattern
     * @return true if it is matches or false if otherwise
     */
    public static boolean hasMatch(final String candidate, final String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(candidate);
        return m.find();
    }


}

package com.shortthirdman.sharedlibs.util;

import com.shortthirdman.sharedlibs.common.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {

    /**
     * Convert a snake-case text string to camel-case
     *
     * @apiNote Capitalize first letter of string. Replace the first occurrence of
     *          letter that present after the underscore, to capitalize form of next
     *          letter of underscore
     * @param text
     * @return the camel-case converted text string
     */
    public static String snakeToCamel(String text) {
        text = text.substring(0, 1).toUpperCase() + text.substring(1);
        while (text.contains(Constants.UNDERSCORE.toString())) {
            text = text.replaceFirst("_[a-z]",
                    String.valueOf(Character.toUpperCase(text.charAt(text.indexOf(Constants.UNDERSCORE.toString()) + 1))));
        }
        return text;
    }

    /**
     * Convert a camel-case text string to snake-case
     *
     * @apiNote Replace the given regex with replacement string and convert it to
     *          lower case.
     * @param text the input string to convert
     * @return snake-case converted text string
     */
    public static String camelToSnake(String text) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        text = text.replaceAll(regex, replacement).toLowerCase();
        return text;
    }

    /**
     * Converts a delimited text string into list of string
     *
     * @param delimitedText
     * @param delimiter
     * @return
     */
    public static List<String> convertToList(String delimitedText, String delimiter) {
        List<String> result = new ArrayList<>();
        try {
            String[] commaSeparatedArr = delimitedText.split(delimiter);
            result = Arrays.stream(commaSeparatedArr).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Checks if the list is valid - whether the list is empty or size is zero
     *
     * @param list the list of object to be checked
     * @return boolean value
     */
    public static Boolean isListValid(List<?> list) {
        if (list != null) {
            if (list.isEmpty()) {
                return Boolean.FALSE;
            } else {
                return Boolean.TRUE;
            }
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * @param textValue
     * @return the trimmed value
     */
    public static String trimText(String textValue) {
        String trimmedText;
        if (textValue == null) {
            trimmedText = null;
        } else {
            trimmedText = textValue.trim();
        }
        return trimmedText;
    }
}

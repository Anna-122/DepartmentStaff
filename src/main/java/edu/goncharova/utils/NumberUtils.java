package edu.goncharova.utils;

public final class NumberUtils {

    public static Integer parseNumber(String number) {
        try {
            return Integer.valueOf(number);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

package com.company.backstagecontentmanagementsystem.util;

public class StringUtils {
    private StringUtils() {
    }

    public static boolean isEmpty(String s) {
        if (s == null || s.length() == 0) {
            return true;
        } else {
            return false;
        }
    }
}

package com.company.backstagecontentmanagementsystem.util;

import java.security.MessageDigest;

public class MD5Utils {

    public static String encode(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(text.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                int a = b & 0xff;
                String hexString = Integer.toHexString(a);
                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (Exception e) {
            // ignored
        }
        return "";
    }
}

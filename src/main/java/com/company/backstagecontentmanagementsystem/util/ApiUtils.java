package com.company.backstagecontentmanagementsystem.util;

import org.springframework.web.util.WebUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class ApiUtils {

    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

    public static boolean checkLogin(String token, HttpServletRequest request) {
        Object session = WebUtils.getSessionAttribute(request, token);
        return session != null && ((int) session) > 0;
    }
}

package com.company.backstagecontentmanagementsystem.config;

import com.company.backstagecontentmanagementsystem.util.ApiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomInterceptor extends HandlerInterceptorAdapter {
    private static final String LOGIN_HTML = "/login.html";
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        logger.info("preHandler");

        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        Cookie cookie = WebUtils.getCookie(request, Constant.USER_TOKEN);
        if (cookie != null) {
            String token = cookie.getValue();
            logger.info("token:{}, url:{}", token, request.getRequestURL());
            if (Constant.NULL_TOKEN.equals(token) || !ApiUtils.checkLogin(token, request)) {
                logger.info("not login, redirect");
                response.sendRedirect(LOGIN_HTML);
                return false;
            }
        } else {
            response.sendRedirect(LOGIN_HTML);
            logger.info("not login, redirect");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.info("postHandle: " + httpServletRequest.getRequestURL());
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.info("afterCompletion: " + httpServletRequest.getRequestURL());
    }
}

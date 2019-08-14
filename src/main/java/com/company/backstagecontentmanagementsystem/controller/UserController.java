package com.company.backstagecontentmanagementsystem.controller;


import com.company.backstagecontentmanagementsystem.config.Constant;
import com.company.backstagecontentmanagementsystem.handler.Result;
import com.company.backstagecontentmanagementsystem.domain.User;
import com.company.backstagecontentmanagementsystem.service.UserService;
import com.company.backstagecontentmanagementsystem.util.ApiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestParam("phone") String phone, @RequestParam("password") String password, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        User user = userService.login(phone, password);
        if (user != null) {
            HttpSession httpSession = httpServletRequest.getSession();
            int expiry = 3600*24;
            String uuid = ApiUtils.getUUID();
            httpSession.setMaxInactiveInterval(expiry);
            httpSession.setAttribute(uuid, user.getUserId());

            Cookie cookie = new Cookie(Constant.USER_TOKEN, uuid);
            cookie.setMaxAge(expiry);
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);
            return Result.createYesResult(uuid);
        } else {
            return Result.createNoResult(Result.ErrorCode.LOGIN_FAILED);
        }
    }

    @ResponseBody
    @PostMapping("/logout")
    public Result logout(@CookieValue(value = Constant.USER_TOKEN, defaultValue = Constant.NULL_TOKEN) String token, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        HttpSession httpSession = httpServletRequest.getSession(false);
        if(httpSession != null){
            httpSession.removeAttribute(token);
        }

        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie:cookies){
            if (Constant.USER_TOKEN.equals(cookie.getName())){
                Cookie cookie1 = new Cookie(cookie.getName(),"");
                cookie1.setMaxAge(0);
                cookie1.setPath("/");
                httpServletResponse.addCookie(cookie1);
            }
        }
        return Result.createYesResult();
    }

    @ResponseBody
    @PostMapping(value = "/query_user")
    public Result queryUserInfo(@CookieValue(value = Constant.USER_TOKEN)  String token, HttpServletRequest request) {
        int userId = (int) WebUtils.getSessionAttribute(request, token);
        User user = userService.findUserById(userId);
        if (user != null) {
            return Result.createYesResult(user);
        } else {
            return Result.createNoResult(Result.ErrorCode.USER_NOT_EXIST);
        }
    }

    @PostMapping("/register")
    @ResponseBody
    public Result register(@RequestParam("phone") String phone, @RequestParam("password") String password) {
        boolean registered = userService.isRegistered(phone);
        if (registered) {
            return Result.createNoResult(Result.ErrorCode.REPEATED_PHONE);
        } else {
            if (userService.register(phone, password)) {
                return Result.createYesResult();
            } else {
                return Result.createNoResult(Result.ErrorCode.REGISTER_FAILED);
            }
        }
    }
}

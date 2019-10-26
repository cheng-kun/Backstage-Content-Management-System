package com.company.backstagecontentmanagementsystem.controller;

import com.company.backstagecontentmanagementsystem.config.Constant;
import com.company.backstagecontentmanagementsystem.domain.Store;
import com.company.backstagecontentmanagementsystem.domain.User;
import com.company.backstagecontentmanagementsystem.response.Result;
import com.company.backstagecontentmanagementsystem.service.StoreService;
import com.company.backstagecontentmanagementsystem.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class StoreController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private StoreService storeService;
    private UserService userService;

    @Autowired
    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @PostMapping("/create")
    public Result createStore(@RequestBody Store store, @CookieValue(Constant.USER_TOKEN) String token,
                              HttpServletRequest request) {
        int userId = (int) WebUtils.getSessionAttribute(request, token);
        logger.info("token:{}, userId:{}", token, userId);
        User user = userService.findUserById(userId);
        store.setUser(user);
        logger.info("user:{}", user);
        boolean ret = storeService.createStore(store);
        if (ret) {
            return Result.createYesResult();
        } else {
            return Result.createNoResult(Result.ErrorCode.CREATE_STORE_FAILED);
        }
    }

    @ResponseBody
    @PostMapping("/store/update")
    public Result updateStore(@RequestBody Store store, HttpServletResponse response) {
        boolean ret = storeService.updateStore(store);
        if (ret) {
            Cookie cookie = new Cookie("store-name", store.getName());
            cookie.setPath("/");
            cookie.setMaxAge(24 * 3600);
            response.addCookie(cookie);
            return Result.createYesResult();
        } else {
            return Result.createNoResult(Result.ErrorCode.UPDATE_STORE_FAILED);
        }
    }

    @ResponseBody
    @PostMapping("/store/query")
    public Result queryStory(@CookieValue(Constant.USER_TOKEN) String token,
                             HttpServletRequest request) {
        int userId = (int) WebUtils.getSessionAttribute(request, token);
        logger.info("token:{}, userId:{}", token, userId);
        Store store = storeService.queryStore(userId);
        if (store != null) {
            return Result.createYesResult(store);
        } else {
            return Result.createNoResult(Result.ErrorCode.QUERY_STORE_FAILED);
        }
    }

    @GetMapping(value = "/store_main.html")
    public ModelAndView storeMain(@CookieValue(Constant.USER_TOKEN) String token,
                                  HttpServletRequest request) {
        return getStoreMv(token, request, "store_main");
    }

    @GetMapping(value = "/store_edit.html")
    public ModelAndView storeEdit(@CookieValue(Constant.USER_TOKEN) String token,
                                  HttpServletRequest request) {
        return getStoreMv(token, request, "store_edit");
    }

    private ModelAndView getStoreMv(String token, HttpServletRequest request, String viewName) {
        ModelAndView modelAndView = new ModelAndView(viewName);
        int userId = (int) WebUtils.getSessionAttribute(request, token);
        logger.info("token:{}, userId:{}", token, userId);
        Store store = storeService.queryStore(userId);
        modelAndView.addObject("store", store);
        return modelAndView;
    }

}

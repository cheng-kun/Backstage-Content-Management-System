package com.company.backstagecontentmanagementsystem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class Controller {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("hello")
    @ResponseBody
    public String sayHello(){
        return "hello, world";
    }

    @GetMapping(value = "/login.html")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/register.html")
    public String registerPage() {
        return "register";
    }

    @GetMapping(value = "/goods_main.html")
    public String goodsMain() {
        return "goods_main";
    }

}

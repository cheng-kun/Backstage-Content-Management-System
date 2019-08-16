package com.company.backstagecontentmanagementsystem.config;

import com.company.backstagecontentmanagementsystem.handler.CustomInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry){
        super.addInterceptors(interceptorRegistry);
        interceptorRegistry.addInterceptor(new CustomInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/login", "/user/register", "/hello");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/register.html").setViewName("register");
        registry.addViewController("/goods_main.html").setViewName("goods_main");
    }

}

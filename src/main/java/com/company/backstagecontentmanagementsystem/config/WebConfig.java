package com.company.backstagecontentmanagementsystem.config;

import com.company.backstagecontentmanagementsystem.handler.CustomInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry){
        super.addInterceptors(interceptorRegistry);
        interceptorRegistry.addInterceptor(new CustomInterceptor()).addPathPatterns("/**").excludePathPatterns("login","register");
    }
}

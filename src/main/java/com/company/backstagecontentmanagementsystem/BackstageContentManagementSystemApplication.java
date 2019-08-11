package com.company.backstagecontentmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BackstageContentManagementSystemApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BackstageContentManagementSystemApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BackstageContentManagementSystemApplication.class);
    }

}
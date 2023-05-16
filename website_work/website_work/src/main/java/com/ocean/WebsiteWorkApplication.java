package com.ocean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan
public class WebsiteWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsiteWorkApplication.class, args);
    }

}

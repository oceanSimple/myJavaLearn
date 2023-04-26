package com.ocean.service;

import com.ocean.config.SpringConfig;
import com.ocean.spring.OceanApplicationContext;

public class Test {
    public static void main(String[] args) {
        OceanApplicationContext oceanApplicationContext = new OceanApplicationContext(SpringConfig.class);
        UserService userService = (UserService) oceanApplicationContext.getBean("userService");
        userService.test();
    }
}

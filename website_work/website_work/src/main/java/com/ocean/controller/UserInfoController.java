package com.ocean.controller;

import com.ocean.common.R;
import com.ocean.entity.UserInfo;
import com.ocean.service.serviceImpl.UserInfoServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoServiceImpl service;

    @PostMapping("/login")
    public R<UserInfo> selectByUsercodeAndUserpwd(@RequestBody UserInfo userinfo, HttpServletResponse response, HttpServletRequest request) {
        return service.selectByUsercodeAndUserpwd(userinfo);
    }

    @PostMapping("/register")
    public R<String> register(@RequestBody UserInfo userinfo) {
        return service.register(userinfo);
    }
}

package com.ocean.mptest.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ocean.mptest.entity.Employee;
import com.ocean.mptest.service.EmployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployService service;
    @GetMapping("/test01")
    public void test01() {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("name","ocean");
        service.list(wrapper).forEach(System.out::println);
    }
}

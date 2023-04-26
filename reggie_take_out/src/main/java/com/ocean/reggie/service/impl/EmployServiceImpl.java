package com.ocean.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocean.reggie.entity.Employee;
import com.ocean.reggie.mapper.EmployMapper;
import com.ocean.reggie.service.EmployService;
import org.springframework.stereotype.Service;

@Service
public class EmployServiceImpl extends ServiceImpl<EmployMapper, Employee> implements EmployService {
}

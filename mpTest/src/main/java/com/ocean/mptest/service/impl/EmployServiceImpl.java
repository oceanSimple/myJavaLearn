package com.ocean.mptest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocean.mptest.entity.Employee;
import com.ocean.mptest.service.EmployService;
import org.springframework.stereotype.Service;
import com.ocean.mptest.mapper.EmployMapper;

@Service
public class EmployServiceImpl extends ServiceImpl<EmployMapper, Employee> implements EmployService {
}

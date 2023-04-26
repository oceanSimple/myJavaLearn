package com.ocean.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ocean.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployMapper extends BaseMapper<Employee> {
}

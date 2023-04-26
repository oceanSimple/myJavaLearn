package com.ocean.mptest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ocean.mptest.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployMapper extends BaseMapper<Employee> {
}

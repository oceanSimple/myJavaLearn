package com.ocean.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocean.reggie.entity.Setmeal;
import com.ocean.reggie.mapper.SetmealMapper;
import com.ocean.reggie.service.SetmealService;
import org.springframework.stereotype.Service;

@Service
public class SetmealServiceimpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
}

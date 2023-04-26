package com.ocean.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ocean.reggie.dto.DishDto;
import com.ocean.reggie.entity.Dish;

public interface DishService extends IService<Dish> {
    void saveWithFlavor(DishDto dishDto);
    DishDto getByIdWithFlavor(Long id);
    void updateWithFlavor(DishDto dishDto);
}

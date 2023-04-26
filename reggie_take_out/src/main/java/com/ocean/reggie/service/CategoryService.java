package com.ocean.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ocean.reggie.entity.Category;

public interface CategoryService extends IService<Category> {
    void remove(Long id);
}

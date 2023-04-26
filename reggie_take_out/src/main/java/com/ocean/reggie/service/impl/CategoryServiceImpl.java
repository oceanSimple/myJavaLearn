package com.ocean.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocean.reggie.common.CustomException;
import com.ocean.reggie.entity.Category;
import com.ocean.reggie.entity.Dish;
import com.ocean.reggie.entity.Setmeal;
import com.ocean.reggie.mapper.CategoryMapper;
import com.ocean.reggie.service.CategoryService;
import com.ocean.reggie.service.DishService;
import com.ocean.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    /**
     * 根据id删除分类，删除前需要判断是否有外连接
     * @param id
     */
    @Override
    public void remove(Long id) {
        //查询当前分类是否关联了菜品， 如果已经关联，抛出一个异常
        LambdaQueryWrapper<Dish> dishWrapper = new LambdaQueryWrapper();
        dishWrapper.eq(Dish::getCategoryId,id);
        long count1 = dishService.count(dishWrapper);
        if (count1 > 0) {   //如果关联，抛出异常
            throw new CustomException("当前分类下关联了菜品，不能删除");
        }

        //查询当前分类是否关联了套餐， 如果已经关联，抛出一个异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        long count2 = setmealService.count(setmealLambdaQueryWrapper);
        if (count2 > 0) {   //如果关联，抛出异常
            throw new CustomException("当前分类下关联了套餐，不能删除");
        }

        // 正常删除
        super.removeById(id);
    }
}

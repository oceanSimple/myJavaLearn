package com.ocean.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ocean.common.R;
import com.ocean.entity.Food;

import java.util.List;

public interface FoodService extends IService<Food> {
    /**
     * 查询所有的food数据
     */
    R<List<Food>> selectAll();

    /**
     * 通过tid查询数据
     * @param tid 所属类别
     * @return  列表
     */
    R<List<Food>> selectByTid(int tid);

    /**
     * 查询所有food数据，并同时查找其Ftype
     */
    R<List<Food>> selectAllWithTableFtype();

    /**
     * 分页查询
     * @param page
     * @param pageSize
     */
    R<Page> page(int page, int pageSize, String input);

    /**
     * 获得数据总数
     */
    R<Long> getDataCount();

    /**
     * 通过fid查询food
     */
    R<Food> selectByFid(int fid);

    /**
     * 修改food
     */
    R<String> modifyByFid(Food food);

    /**
     * 通过fid删除food

     */
    R<String> deleteByFid(int fid);

    /**
     * 新增food
     */
    R<String> addFood(Food food);
}

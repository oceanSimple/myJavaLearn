package com.ocean.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ocean.common.R;
import com.ocean.entity.Food;
import com.ocean.service.serviceImpl.FoodServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    private FoodServiceImpl foodService;

    @GetMapping("/selectAll")
    public R<List<Food>> selectAll() {
        return foodService.selectAll();
    }

    @GetMapping("/selectByTid")
    public R<List<Food>> selectByTid(int tid) {
        return foodService.selectByTid(tid);
    }

    @GetMapping("/selectByFid")
    public R<Food> selectByFid(int fid) {
        return foodService.selectByFid(fid);
    }

    @GetMapping("/selectAllWithTableFtype")
    public R<List<Food>> selectAllWithTableFtype() {
        return foodService.selectAllWithTableFtype();
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String input) {
        return foodService.page(page, pageSize, input);
    }

    @GetMapping("/count")
    public R<Long> getDataCount() {
        return foodService.getDataCount();
    }

    @PostMapping("/modifyByFid")
    public R<String> modifyByFid(@RequestBody Food food) {
        return foodService.modifyByFid(food);
    }

    @GetMapping("/deleteByFid")
    public R<String> deleteByFid(@RequestParam int fid) {
        return foodService.deleteByFid(fid);
    }

    @PostMapping("/addFood")
    public R<String> addFood(@RequestBody Food food) {
        return foodService.addFood(food);
    }
}

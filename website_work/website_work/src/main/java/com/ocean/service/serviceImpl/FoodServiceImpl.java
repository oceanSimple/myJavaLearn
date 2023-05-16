package com.ocean.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocean.common.R;
import com.ocean.entity.Food;
import com.ocean.entity.Ftype;
import com.ocean.mapper.FoodMapper;
import com.ocean.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food> implements FoodService {
    @Autowired
    private FtypeServiceImpl ftypeService;

    @Value("${website.imagePath}")
    private String imagePath;

    @Override
    public R<List<Food>> selectAll() {
        List<Food> list = this.list();
        if (list == null) {
            return R.error("查找失败");
        }
        addPicturePrefix(list);
        return R.success(list);
    }

    @Override
    public R<List<Food>> selectByTid(int tid) {
        QueryWrapper<Food> wrapper = (QueryWrapper<Food>) new QueryWrapper().eq("tid",tid);
        List<Food> list = list(wrapper);
        if (list == null) {
            return R.error("查询失败");
        }
        addPicturePrefix(list);
        return R.success(list);
    }

    @Override
    public R<List<Food>> selectAllWithTableFtype() {
        R<List<Food>> listFood = selectAll();
        R<List<Ftype>> listFtype = ftypeService.selectAll();
        addPicturePrefix(listFood.getData());
        matchFoodAndFtype(listFood.getData(),listFtype.getData());
        return listFood;
    }

    @Override
    public R<Page> page(int page, int pageSize, String input) {
        // 创建pageInfo对象
        // Page中的records属性保存了查询的结果
        Page<Food> pageInfo = new Page<>(page, pageSize);
        // 条件构造器
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.orderByAsc("fid");
        wrapper.like("fname", input);
        // 进行分页查询
        page(pageInfo,wrapper);
        matchFoodAndFtype(pageInfo.getRecords(),ftypeService.selectAll().getData());
        addPicturePrefix(pageInfo.getRecords());

        for (Food record : pageInfo.getRecords()) {
            record.setFdesc(record.getFdesc().replace("<br>", ""));
        }
        return R.success(pageInfo);
    }

    @Override
    public R<Long> getDataCount() {
        long count = count();
        return R.success(count);
    }

    @Override
    public R<Food> selectByFid(int fid) {
        QueryWrapper<Food> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fid", fid);
        Food food = getOne(queryWrapper);
        addPicturePrefix(food);
        return R.success(food);
    }

    @Override
    public R<String> modifyByFid(Food food) {
        QueryWrapper<Food> wrapper = new QueryWrapper<>();
        wrapper.eq("fid", food.getFid());
        Food one = getOne(wrapper);
        food.setForder(one.getForder());
        food.setFregtime(one.getFregtime());
        // 修改图片的路径，去掉前面的请求路径，只保留文件名
        food.setFpic(food.getFpic().substring(food.getFpic().lastIndexOf("/") + 1));
        boolean b = update(food, wrapper);
        if (b) {
            return R.success("修改成功");
        }else {
            return R.error("修改失败");
        }
    }

    @Override
    public R<String> deleteByFid(int fid) {
        QueryWrapper<Food> wrapper = new QueryWrapper<>();
        wrapper.eq("fid", fid);
        boolean flag = remove(wrapper);
        if (flag) {
            return R.success("删除成功");
        }else {
            return R.error("删除失败！");
        }
    }

    @Override
    public R<String> addFood(Food food) {
        // 修改图片的路径，去掉前面的请求路径，只保留文件名
        food.setFpic(food.getFpic().substring(food.getFpic().lastIndexOf("/") + 1));
        // 添加创建时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String currentDate = format.format(date);
        food.setFregtime(currentDate);

        food.setForder(1);

        boolean flag = save(food);
        if (flag) {
            return R.success("添加成功");
        }else {
            return R.error("添加失败");
        }
    }

    /**
     * 将food的tid与ftype匹配
     * @param foodList
     * @param ftypeList
     */
    public void matchFoodAndFtype(List<Food> foodList, List<Ftype> ftypeList) {
        for (Food food : foodList) {
            for (Ftype ftype : ftypeList) {
                if (food.getTid().equals(ftype.getTid())) {
                    food.setFtype(ftype);
                }
            }
        }
    }

    /**
     * 给图片的路径加上绝对路径的前缀
     * @param foodList
     */
    public void addPicturePrefix(List<Food> foodList) {
        for (Food food : foodList) {
            food.setFpic(imagePath + "/foodImages/" + food.getFpic());
        }
    }

    /**
     * 给图片的路径加上绝对路径的前缀
     * @param food
     */
    public void addPicturePrefix(Food food) {
        food.setFpic(imagePath + "/foodImages/" + food.getFpic());
    }


}

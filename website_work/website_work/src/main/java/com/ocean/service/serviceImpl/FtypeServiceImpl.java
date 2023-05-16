package com.ocean.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocean.common.R;
import com.ocean.entity.Ftype;
import com.ocean.mapper.FtypeMapper;
import com.ocean.service.FtypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FtypeServiceImpl extends ServiceImpl<FtypeMapper, Ftype> implements FtypeService {
    @Override
    public R<Ftype> selectByTid(int tid) {
        QueryWrapper<Ftype> wrapper = (QueryWrapper<Ftype>) new QueryWrapper().eq("tid",tid);
        Ftype one = getOne(wrapper);
        if (one == null) {
            return R.error("查询错误，请重新输入tid");
        }
        return R.success(one);
    }

    @Override
    public R<List<Ftype>> selectAll() {
        List<Ftype> list = list();
        if (list == null) {
            return R.error("查询错误");
        }
        return R.success(list);
    }
}

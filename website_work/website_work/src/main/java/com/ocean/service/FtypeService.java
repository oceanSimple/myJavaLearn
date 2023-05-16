package com.ocean.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ocean.common.R;
import com.ocean.entity.Ftype;

import java.util.List;

public interface FtypeService extends IService<Ftype> {
    R<Ftype> selectByTid(int tid);
    R<List<Ftype>> selectAll();
}

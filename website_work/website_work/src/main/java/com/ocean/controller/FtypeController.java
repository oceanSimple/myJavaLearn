package com.ocean.controller;

import com.ocean.common.R;
import com.ocean.entity.Ftype;
import com.ocean.service.serviceImpl.FtypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ftype")
public class FtypeController {
    @Autowired
    private FtypeServiceImpl service;

    @GetMapping("/selectAll")
    public R<List<Ftype>> selectAll() {
        return service.selectAll();
    }

    @GetMapping("/selectByTid")
    public R<Ftype> selectByTid(int tid) {
        return service.selectByTid(tid);
    }
}

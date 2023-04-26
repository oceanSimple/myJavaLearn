package com.ocean.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ocean.reggie.common.R;
import com.ocean.reggie.entity.Employee;
import com.ocean.reggie.service.EmployService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployController {
    @Autowired
    private EmployService employService;

    /**
     * 员工登录
     */
    @PostMapping("/login")
    public R<Employee> login(@RequestBody Employee employee, HttpServletRequest request) {
        //1. 将页面提交的密码进行md5加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2. 根据用户名查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employService.getOne(queryWrapper);

        //3. 如果没找到,返回失败结果
        if (emp == null) {
            return R.error("登录失败");
        }

        //4. 密码对比,如果不一致返回失败结果
        if (!Objects.equals(emp.getPassword(),password)) {
            return R.error("登录失败");
        }

        //5. 账号密码均正确,查看账户状态是否为已禁用状态
        if (emp.getStatus() == 0) { //0表示禁用,1表示正常使用
            return R.error("账号已禁用");
        }

        //6. 登录成功,将用户id放入session中
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);
    }

    /**
     *员工退出
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        //清理session中保存的当前登录员工的id
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    /**
     *新增员工
     */
    @PostMapping
    public R<String> save(@RequestBody Employee employee, HttpServletRequest request) {
        // 将密码进行md5加密,默认密码"123456"
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        //由公共配置类设置
        /*
        // 手动设置时间等信息
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        // 设置操作对象
        employee.setCreateUser((Long) request.getSession().getAttribute("employee"));
        employee.setUpdateUser((Long) request.getSession().getAttribute("employee"));
        */

        employService.save(employee);
        return R.success("新增员工成功!");
    }

    /**
     * 员工信息分页查询
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        // 构造分页构造器
        Page pageInfo = new Page(page,pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
            //ps: StringUtils.isNotEmpty(name)
            //在import org.apache.commons.lang3.StringUtils;包下
        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);
        //添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        //执行查询
        employService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }

    /**
     * 根据id修改员工信息
     */
    @PutMapping
    public R<String> update(@RequestBody Employee employee, HttpServletRequest request) {
        //由公共配置类设置
        /*
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser((Long) request.getSession().getAttribute("employee"));
        */
        employService.updateById(employee);
        return R.success("员工信息修改成功");
    }

    /**
     * 根据id查询员工信息
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id) {
        log.info("查询id：",id);
        Employee byId = employService.getById(id);
        if (byId != null) {
            return R.success(byId);
        }
        return R.error("没有查到员工信息");
    }

}

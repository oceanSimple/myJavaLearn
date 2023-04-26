package com.ocean.service;

import com.ocean.spring.Autowired;
import com.ocean.spring.BeanNameAware;
import com.ocean.spring.Component;
import com.ocean.spring.InitializingBean;

@Component("userService")
public class UserService implements BeanNameAware, InitializingBean {
    @Autowired
    private OrderService orderService;

    private String beanName;

    private String someParam;

    public void test() {
        System.out.println(orderService);
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("init...");
    }
}

package com.ocean.service;

import com.ocean.spring.BeanPostProcessor;
import com.ocean.spring.Component;

@Component
public class OceanBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(String beanName, Object bean) {
        if (beanName.equals("userService")) {
            System.out.println("1111");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(String beanName, Object bean) {
        if (beanName.equals("userService")) {
            System.out.println("2222");
        }
        return bean;
    }
}

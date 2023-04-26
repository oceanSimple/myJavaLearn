package com.ocean.test;

import com.ocean.config.SpringConfig;
import com.ocean.spring.ComponentScan;
import com.ocean.spring.OceanApplicationContext;

public class ClassLoaderLearn {
    public static void main(String[] args) {
        Class configClass = SpringConfig.class;
        ComponentScan componentScanAnnotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
        String path = componentScanAnnotation.value().replace(".","/");  //将com.ocean.service替换为com/ocean/service

        ClassLoader classLoader = OceanApplicationContext.class.getClassLoader();
        System.out.println(classLoader.getResource(path));
        System.out.println(classLoader.getResource(path).getFile());
    }
}

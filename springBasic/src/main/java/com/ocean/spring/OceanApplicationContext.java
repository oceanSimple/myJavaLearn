package com.ocean.spring;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class OceanApplicationContext {
    private Class configClass;

    private ConcurrentHashMap<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String,Object> singletonObjects = new ConcurrentHashMap<>();
    private ArrayList<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    public OceanApplicationContext(Class springConfigClass) {
        this.configClass = springConfigClass;

        //扫描
        if (springConfigClass.isAnnotationPresent(ComponentScan.class)) {//首先判断给的配置类是否含有ComponentScan注解
            //获取注解的value-扫描路径（包名）
            ComponentScan componentScanAnnotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
            String path = componentScanAnnotation.value().replace(".","/");  //将com.ocean.service替换为com/ocean/service

            ClassLoader classLoader = OceanApplicationContext.class.getClassLoader();
            URL resource = classLoader.getResource(path);   //获取路径：file:/D:/git/myJavaLearn/springBasic/target/classes/com/ocean/service
            File file = new File(resource.getFile());   //获取文件夹：/D:/git/myJavaLearn/springBasic/target/classes/com/ocean/service


            if (file.isDirectory()) {//判断file是不是一个文件夹
                File[] files = file.listFiles();
                for (File f: files) {
                    String fileName = f.getAbsolutePath();

                    if (fileName.endsWith(".class")) {
                        Class<?> clazz;
                        try {
                            String className = fileName.substring(fileName.indexOf("com"), fileName.indexOf(".class"));
                            className = className.replace("\\",".");
                            clazz = classLoader.loadClass(className);
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }

                        if (clazz.isAnnotationPresent(Component.class)) {
                            //判断clazz是否继承了BeanPost
                            if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                                try {
                                    BeanPostProcessor instance = (BeanPostProcessor) clazz.newInstance();
                                    beanPostProcessorList.add(instance);
                                } catch (InstantiationException e) {
                                    throw new RuntimeException(e);
                                } catch (IllegalAccessException e) {
                                    throw new RuntimeException(e);
                                }
                            }

                            String beanName = clazz.getAnnotation(Component.class).value();
                            //@component上没写value，根据类名生成
                            if ("".equals(beanName)) {
                                beanName = Introspector.decapitalize(clazz.getSimpleName());
                            }

                            BeanDefinition beanDefinition = new BeanDefinition();
                            beanDefinition.setType(clazz);
                            //判断是否有scope注解：单例或多例bean
                            if (clazz.isAnnotationPresent(Scope.class)) {
                                Scope scopeAnnotation = clazz.getAnnotation(Scope.class);
                                beanDefinition.setScope(scopeAnnotation.value());
                            } else {
                                beanDefinition.setScope("singleton");
                            }

                            beanDefinitionMap.put(beanName,beanDefinition);

                        }
                    }
                }
            }

        }

        //创建单例bean对象
        for (String beanName : beanDefinitionMap.keySet()) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (beanDefinition.getScope().equals("singleton")) {
                //单例
                Object bean = createBean(beanName,beanDefinition);
                singletonObjects.put(beanName, bean);
            }
        }

    }

    private Object createBean(String name, BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getType();

        try {
            //无参构造方法
            Object instance = clazz.getConstructor().newInstance();
            //依赖注入的实现
            for (Field f : clazz.getDeclaredFields()) {
                if (f.isAnnotationPresent(Autowired.class)) {
                    f.setAccessible(true);
                    f.set(instance,getBean(f.getName()));
                }
            }

            //Aware回调
            if (instance instanceof BeanNameAware) {
                ((BeanNameAware) instance).setBeanName(name);
            }

            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                beanPostProcessor.postProcessBeforeInitialization(name, instance);
            }

            //初始化
            if (instance instanceof InitializingBean) {
                ((InitializingBean) instance).afterPropertiesSet();
            }

            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                beanPostProcessor.postProcessAfterInitialization(name, instance);
            }

            return instance;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public Object getBean(String name) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) {
            throw new NullPointerException();
        } else {
            String scope = beanDefinition.getScope();
            if ("singleton".equals(scope)) {
                //单例
                Object bean = singletonObjects.get(name);
                if (bean == null) {
                    Object bean1 = createBean(name, beanDefinition);
                    singletonObjects.put(name,bean1);
                    return bean1;
                }
                return bean;
            } else {
                //多例
                return createBean(name,beanDefinition);
            }
        }
    }
}

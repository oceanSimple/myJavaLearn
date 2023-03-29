package annotation;

import annotation.myAnnotations.Annotation01;
import reflect.entity.Student;

import java.lang.reflect.Field;

/**
 * 模拟前端http报文中提交的请求
 * 例如 name=ocean&age=20&code=123456
 */
public class Demo01 {
    @Annotation01("name=ocean&age=20&code=123456")
    private String params;

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        //处理params上面的注解所传来的信息
        Class<?> aClass = Class.forName("annotation.Demo01");
        //获取params成员变量
        Field params = aClass.getDeclaredField("params");
        //获取params的注解信息
        Annotation01 annotation = params.getAnnotation(Annotation01.class);
        String value = annotation.value();
        System.out.println(value);
    }
}

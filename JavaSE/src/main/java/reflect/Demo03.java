package reflect;

import reflect.entity.Student;
import java.lang.reflect.Field;

/**
 * 对成员变量的操作
 */
public class Demo03 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class<?> aClass = Class.forName("reflect.entity.Student");

        //获取所有的公共字段
        Field[] allFields = aClass.getFields();
        //获取所有字段，包括private等
        Field[] allDeclaredFields = aClass.getDeclaredFields();

        //获取特定的字段
        Field name = aClass.getDeclaredField("name");

        //ps：该set方法无法为private字段赋值，故本程序会报错
        Student student = new Student();
        name.set(student,"ocean");
        System.out.println(student);
    }
}

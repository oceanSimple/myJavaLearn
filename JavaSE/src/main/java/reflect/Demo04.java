package reflect;

import reflect.entity.Student;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 对成员方法的操作
 */
public class Demo04 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> aClass = Class.forName("reflect.entity.Student");

        //方式与获取构造方法类似
        Method[] methods = aClass.getMethods();
        Method getAge = aClass.getMethod("getAge");
        Method setAge = aClass.getMethod("setAge", int.class);

        Student student = new Student("ocean", "123456", 20);
        setAge.invoke(student,18);
        System.out.println(getAge.invoke(student));
    }
}

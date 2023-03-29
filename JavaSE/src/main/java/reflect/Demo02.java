package reflect;

import reflect.entity.Student;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 获取类的构造方法以及通过构造方法创建对象
 */
public class Demo02 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //获取Student的class对象
        Class<?> aClass = Class.forName("reflect.entity.Student");

        //获取所有的public构造方法
        Constructor<?>[] allConstructors = aClass.getConstructors();
        //获取所有的构造方法(包括public、private等等)
        Constructor<?>[] allDeclaredConstructors = aClass.getDeclaredConstructors();

        //获取单个的无参构造器
        Constructor<?> aClassConstructor1 = aClass.getConstructor(null);
        //获取单个的含参构造方法
        Constructor<?> aClassConstructor2 = aClass.getConstructor(String.class, String.class, int.class);
        Student ocean = (Student) aClassConstructor2.newInstance("ocean", "20216928", 20);
    }
}

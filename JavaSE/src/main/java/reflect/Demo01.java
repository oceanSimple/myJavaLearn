package reflect;

import reflect.entity.Student;

/**
 * 三种获取class对象的方法
 */
public class Demo01 {
    public static void main(String[] args) {
        //获取class的三种方式
        //1.通过实例对象获取
        Student student = new Student();
        Class class1 = student.getClass();
        //2.通过类名获取
        Class<Student> class2 = Student.class;
        //3.通过类的真实路径获取(常用！)
        Class class3;
        try {
            class3 = Class.forName("reflect.entity.Student");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //判断获得的三个class对象是否相同
        boolean flag1 = (class1 == class2) && (class2 == class3) && (class1 == class3);
        System.out.println("判断获得的三个class对象是否相同："+ flag1);
        System.out.println("输出class对象："+class1);
    }
}

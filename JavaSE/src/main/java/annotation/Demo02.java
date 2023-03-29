package annotation;

import annotation.myAnnotations.Annotation02;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

@Annotation02(name = "ocean" , code = "123456")
public class Demo02 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException {
        Class<?> aClass = Class.forName("annotation.Demo02");
        Annotation02 annotation = aClass.getAnnotation(Annotation02.class);

        Class<? extends Annotation02> aClass1 = annotation.getClass();
        Method[] fields = aClass1.getMethods();
        HashMap<String, String> map = new HashMap<>();
        for (Method field: fields) {
            /*map.put(field.getName(), (String) field.);*/
            System.out.println(field.getName());
        }
        map.forEach((key,value) -> System.out.println(key + ":" +value));

    }
}

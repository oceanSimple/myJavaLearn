package util;

import listLearn.entity.MyArrayList;
import org.testng.annotations.Test;

import java.util.Arrays;

@Test
public class MyArrayListTest {
    private MyArrayList<Integer> list;
    public MyArrayListTest(){
        this.list = new MyArrayList<>();
    }

    /**
     * 测试add方法
     */
    public void addTest() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(11);
        list.add(12);
        System.out.println(list.toString());
    }

    /**
     * 测试set方法
     */
    public void setTest() {
        list.add(0);
        list.add(1);
        list.add(3);
        list.set(2,2);
        System.out.println(list);
    }

    /**
     * 测试insert方法
     */
    public void insertTest(){
        list.add(0);
        list.add(1);
        list.add(3);
        list.insert(2,2);
        list.insert(4,4);
        System.out.println(list);
    }

    /**
     * 测试remove
     */
    public void removeTest(){
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(4);
        list.remove(2);
        list.add(5);
        System.out.println(list);
    }

    /**
     * 测试迭代器
     */
    public void iteratorTest(){
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        for (int listNode:list) {
            System.out.println(listNode);
        }
    }

    /**
     * 测试查找方法
     */
    public void indexOfTest(){
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(null);
        list.add(3);
        System.out.println(list.indexOf(2));
        System.out.println(list.indexOf(4));
        System.out.println(list.indexOf(null));
    }

    /**
     * 测试转化数组方法
     */
    public void toArrayTest(){
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(Arrays.toString(list.toArray()));
    }
}

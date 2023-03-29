package listLearn.entity;

import java.util.Arrays;
import java.util.Iterator;

/**
 * myOwnArrayList
 * @author ocean
 * @version 2023/03/27
 */
public class MyArrayList<T> implements Iterable<T>{
    /**
     * 默认数组的初始化长度
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 存放数据的数组
     */
    private T[] data;

    /**
     * 存放数据的长度
     */
    private int size;

    /**
     * 数组的总长度
     */
    private int length;

    /**
     * 用来定义扩容规则，若为0，采用系统的扩容方式；若为其他正数，则按照该值进行扩容
     */
    private int incrementSize;

    /**
     * 无参构造方法
     */
    public MyArrayList(){
        this(DEFAULT_CAPACITY);
    }

    /**
     * 含参构造方法，用户指定数组的初始长度
     * @param initLength 初始化长度
     */
    public MyArrayList(int initLength){
        if (initLength < 0) {
            initLength = DEFAULT_CAPACITY;
        }
        this.size = 0;
        this.incrementSize = 0;
        this.length = initLength;
        this.data = (T[]) new Object[initLength];
    }

    /**
     * 含参构造方法，将用户传入的数组初始化
     * @param ele 传入的数组
     */
    public MyArrayList(T[] ele){
        this.length = ele.length;
        this.size = ele.length;
        this.incrementSize = 0;
        data = Arrays.copyOf(ele,length);
    }

    /**
     * 判断数组容量是否已满
     * @return 返回判断结果
     */
    public boolean isFull(){
        return this.size > this.length - 1;
    }

    /**
     * 设置自定义扩容方式
     * @param incrementSize 每次扩容的大小
     */
    public void setIncrementSize(int incrementSize){
        if (size > 0)
            this.incrementSize = incrementSize;
    }

    /**
     * 数组扩容方法
     * 当incrementSize = 0 采用系统默认方法，×1.5；当incrementSize 大于 0 采用用户定义方法
     */
    public void incrementCapacity(){
        int newLength;
        if (this.incrementSize == 0) {
            //默认扩容方法，容量×1.5
            newLength = (int)(this.length * 1.5);
        }else {
            newLength = this.length + this.incrementSize;
        }
        this.length = newLength;
        this.data = Arrays.copyOf(data,newLength);
    }

    /**
     * 判断索引值是否合法
     * @param index 索引值
     */
    public void ensureIndex(int index) {
        if (index >= this.size){
            throw new IndexOutOfBoundsException();
        }else if (index < 0 ){
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * 在数组末尾添加数据
     * @param newData 要插入的数据
     */
    public void add(T newData){
        //判断数组容器是否已满
        if (isFull()) {
            incrementCapacity();
        }
        this.data[this.size] = newData;
        this.size ++;
    }

    /**
     * 修改索引值处的数据
     * @param index 要修改的索引值
     * @param newData 修改后的数据
     */
    public void set(int index,T newData){
        ensureIndex(index);
        this.data[index] = newData;
    }

    /**
     * 返回要索引值下要获得的数据
     * @param index 索引值
     * @return 需要的数据
     */
    public T get(int index){
        ensureIndex(index);
        return this.data[index];
    }

    /**
     * 获取数组长度
     */
    public int getSize(){
        return this.size;
    }

    /**
     * 插入方法
     */
    public void add(int index,T newData){
        isFull();
        //判断是否为末尾插入
        if (index == this.size) {
            add(newData);
        } else {
            ensureIndex(index);
            if (this.size - index >= 0) System.arraycopy(this.data, index, this.data, index + 1, this.size - index);
            this.data[index] = newData;
            this.size++;
        }
    }

    /**
     * 插入方法
     */
    public void insert(int index,T newData){
        add(index, newData);
    }

    /**
     * 删除方法
     */
    public void remove(int index){
        ensureIndex(index);
        if (this.size - 1 - index >= 0) System.arraycopy(this.data, index + 1, this.data, index, this.size - 1 - index);
        data[this.size - 1] = null;
        this.size --;
    }

    /**
     * 查找方法（注意对null值的判断！）
     */
    public int indexOf(T o){
        //注意，list中允许存在null值，搜索的对象同样允许传入null值
        //因此要先对o进行null判断
        if (o == null) {
            for (int i = 0; i < this.size; i++) {
                if (data[i] == null) {
                    return i;
                }
            }
        }else {
            for (int i = 0; i < this.size; i++) {
                if (o.equals(this.data[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 返回数组长度
     */
    public int size(){
        return this.size;
    }

    /**
     * 返回数组
     */
    public T[] toArray(){
        return Arrays.copyOf(this.data,this.size);
    }

    /**
     * 实现迭代器，foreach方法
     */
    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator<T> {
        private int index = 0;
        @Override
        public boolean hasNext() {
            return index != size;
        }

        @Override
        public T next() {
            index++;
            return get(index - 1);
            //等价于
            //return get(index++)
        }
    }

    /**
     * 重写toString方法
     */
    public String toString (){
        return Arrays.toString(toArray());
    }
}

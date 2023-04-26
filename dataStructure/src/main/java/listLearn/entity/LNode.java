package listLearn.entity;

public class LNode<T> implements Comparable<LNode<T>> {
    public T data;
    public LNode<T> next;

    public LNode(T key , LNode<T> next) {
        this.data = key;
        this.next = next;
    }

    public boolean equals (Object o) {
        LNode<T> node = (LNode<T>) o;
        return data.equals(node.data);
    }

    /**
     * 重写compareTo方法
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(LNode<T> o) {
        Comparable<T> x;
        if (data instanceof Comparable) {
            x = (Comparable<T>) data;
            return x.compareTo(o.data);
        }else {
            throw new ClassCastException("类型无法比较！");
        }
    }

    public String toString() {
        return this.data.toString();
    }
}

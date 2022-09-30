package codes.list.impl;

import codes.list.MyList;

/**
 * 详细说明见接口
 * @param <T>
 */
public class SeqList<T> implements MyList<T> {
    /**
     * MAX_SIZE：固定容量（禁止扩容）
     * T 使用泛型，使用类型转换，父接口引用指向子类对象，之后类型转换
     * last 是指向最后一个元素的指针，若空则为 -1
     */
    static final int MAX_SIZE = 1000;

    private T[] array = (T[]) new Object[MAX_SIZE];

    private int last = -1;

    @Override
    public void add(T val) throws RuntimeException{
        if(isFull()) throw new RuntimeException("链表已满");
        else array[ ++ last] = val;
    }

    @Override
    public void add(T val, int idx) {
        if( idx < 0 || idx > last + 1) throw new RuntimeException("非法索引");
        else{
            for(int i = (++ last) ; i > idx ; i --){
                array[i] = array[i - 1];
            }
            array[idx] = val;
        }
    }

    @Override
    public void delete(int idx) {
        if(isEmpty()) throw new RuntimeException("链表为空");
        else{
            for( int i = idx; i < last ; i ++ ){
                array[i] = array[i + 1];
            }
            last --;
        }
    }

    @Override
    public void delete() {
        if (isEmpty()) throw new RuntimeException("链表已空");
        -- last;
    }

    @Override
    public void update(int idx, T val) {
        if( idx < 0 || idx > last) throw new RuntimeException("非法索引");
        array[idx] = val;
    }

    @Override
    public T query(int idx) {
        if( idx < 0 || idx > last) throw new RuntimeException("非法索引");
        return array[idx];
    }

    @Override
    public boolean isEmpty() {
        return last == -1;
    }

    @Override
    public boolean isFull() {
        return last == MAX_SIZE - 1;
    }

    @Override
    public int size() {
        return last + 1;
    }
}

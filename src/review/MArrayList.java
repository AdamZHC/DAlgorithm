package review;

public class MArrayList {
    //应当以下几个成员变量
    //当前最大的元素数
    int MAXSIZE = 1000;
    //对应的数组
    int[] arr;
    //当前的指针指向位置——有元素的后一位
    int p = 0;

    //构造函数——无参构造函数
    public MArrayList() {
        arr = new int[MAXSIZE];
    }
    //表示最大容量
    public MArrayList(int capacity) {
        arr = new int[capacity];
    }
    //增加
    public void insert(int idx, int element) {
        if(p == MAXSIZE || !check(idx)){
            System.out.println("wrong idx");
            return;
        }
        for(int i = p++; i > idx; --i)  arr[i] = arr[i - 1];
        arr[idx] =  element;
    }
    //删除
    public void delete(int idx) {
        if(p == 0 || !check(idx)){
            System.out.println("wrong idx");
            return;
        }
        for(int i = idx; i < p--; ++i) arr[i] = arr[i + 1];
    }
    //修改
    public void update(int idx, int element) {
        if(!check(idx)) {
            System.out.println("wrong idx");
            return;
        }
        arr[idx] = element;
    }
    //查询
    public int query(int idx) {
        if(!check(idx)) {
            System.out.println("wrong idx");
            return 0x7fffffff;
        }
        return arr[idx];
    }

    //判定idx是否合法
    boolean check(int idx) {
        //如果idx大于最大容量的话，那也不满足下面的条件——因为p的最大也就是小于MAXSIZE
        return idx < p;
    }

}

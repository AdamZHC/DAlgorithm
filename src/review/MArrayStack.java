package review;


//运算符匹配——简单的严格单调递增栈——哨兵
public class MArrayStack {
    int MAXSIZE = 1000;
    int[] arr;
    //同之前的线性表一样的
    int p = 0;

    public MArrayStack() {
        arr = new int[MAXSIZE];
    }

    public MArrayStack(int capacity) {
        MAXSIZE = capacity;
        arr = new int[capacity];
    }

    public int pop() {
        if(p == 0){
            System.out.println("empty");
            return 0x7fffffff;
        }
        return arr[--p];
    }

    public void push(int element) {
        if(p == MAXSIZE){
            System.out.println("full");
            return ;
        }
        arr[p++] = element;
    }

}

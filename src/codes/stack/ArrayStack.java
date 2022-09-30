package codes.stack;

public class ArrayStack {
    public static void main(String args[]){
        System.out.println(""+new Stack(2).getTop());
        int  num=1;
        System.out.println("ASD");
    }
}
class Stack{
    private int maxSize;
    int top;
    private int [] arr;
    public Stack(int maxSize){
        this.maxSize = maxSize;//方便索引
        top = -1;//栈顶指针初始化时赋为-1
        arr = new int[maxSize];//创建对应的栈实体
    }
    public void push(int num){
        if(top == maxSize-1){
            System.out.println("栈满");
            return;
        }
        top++;//栈顶指针上移
        arr[top] = num;//栈实体赋值
    }
    public int pop(){
        if(top==-1){
            System.out.println("栈空");
            return 0;
        }
//        return arr[top--];
//        或者
        top--;
        return arr[top+1];
    }
    public int getTop(){
        if(top == -1){
            return 0;
        }
        return arr[top];
    }
}

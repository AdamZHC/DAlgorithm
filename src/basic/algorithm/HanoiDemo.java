package basic.algorithm;

import java.util.Arrays;

public class HanoiDemo {
    Stack towerA;
    Stack towerB;
    Stack towerC;

    /**
     * 初始化该汉诺塔
     * @param n//表示总共需要移动的总圆盘数
     */
    public HanoiDemo(int n){
        towerA = new Stack(n);
        towerB = new Stack(n);
        towerC = new Stack(n);
        for (int i = 0; i < n; i++) {
            towerA.push(n-i);
        }
        //这样有了初始化的三个塔
    }
    public static void main(String[] args){
        HanoiDemo test = new HanoiDemo(3);
        hanoi(3, test.towerA, test.towerB,test.towerC);

        //作为非静态方法，把自身类里面的变量传进去难免有些奇怪
    }
    public static void hanoi(int n,Stack src, Stack dst,Stack bystander){
        //参数表示移动的第几个塔，从哪个塔移动到哪个塔
        if(n==1){
            dst.push(src.pop());
            System.out.println(Arrays.toString(src.arr));
            System.out.println(Arrays.toString(dst.arr));
            System.out.println(Arrays.toString(bystander.arr));
            System.out.println();
        }else{
                hanoi(n-1,src,bystander,dst);
                hanoi(1,src,dst,bystander);
                hanoi(n-1,bystander,dst,src);
        }
    }
}
//用三个栈来模拟三个塔
class Stack{
    int top = -1;
    int[] arr;
    public Stack(int capacity){
        arr = new int[capacity];
    }
    public void push(int num){
        arr[++top] = num;
    }
    public int pop(){
        int num = arr[top];
        arr[top--] = 0;
        return num;
    }

}


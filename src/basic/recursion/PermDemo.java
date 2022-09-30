package basic.recursion;

import java.util.*;
public class PermDemo {
    Queue queue;
    Stack stack;
    public static void main(String[] args){
        PermDemo test = new PermDemo(3);
        test.perm(3);
    }
    public PermDemo(int n){
        queue = new Queue(n);
        stack = new Stack(n);
        for(int i = 0; i<n ; i++){
            queue.enqueue(i+1);
        }
    }
    public void perm(int n){
        if(n == 0){
            System.out.println(Arrays.toString(stack.arr));
        }
        for(int i = 0;i < n; i++){
            //上午不可以的原因就是每次出队的没法保存，而且入队列时进去的顺序很奇怪
            //上午的修改方法就是遍历队列的方法的问题，但是那样就太麻烦了，有简单的方法不更好吗啊？
            //其实是有规律的，但是看不出来，我觉得我已经似懂非懂地解决了一个大问题，一个回溯的很难的问题，没必要现在就去
            //完全解决，过几天说不定就全部理解了，现在先不管吧
            //思路就是：(不过分苛求)
            //分解子问题，一定是递归，一个一个遍历一定是队列，根据逻辑在函数体内的思想，在中间，只需要一个栈保存结果
            //全排列就是栈+队列 --> 队列恰好有每次出队入队不同的特性
            stack.push(queue.dequeue());
            perm(n-1);
            queue.enqueue(stack.pop());
        }
    }
}
class Queue{
    int front = 0;
    int rear = 0;
    int[] arr;
    public Queue(int capacity){
        arr = new int[capacity+1];
    }
    public void enqueue(int num){
        arr[rear] = num;
        rear = (rear+1)%arr.length;
//        arr[rear]=0;
    }
    public int dequeue(){
        int num = arr[front];
//        arr[front] = 0;
        front = (front+1)%arr.length;
        return num;
    }
}
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
        return arr[top--];
    }

}

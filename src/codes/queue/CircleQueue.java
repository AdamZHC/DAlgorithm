package codes.queue;

public class CircleQueue {
}
class QueueCircle{
    private int MaxSize;
    private int front ;
    private int rear  ;
    private int[] arr;
    public QueueCircle(int max){
        MaxSize=max;
        arr = new int[MaxSize];
        front=0;//与第一种队列是不一样的，此时front指向队列第一个元素
        rear=0;//rear指向队列最后一个元素的后面一个位置
    }
    public boolean isFull(){
        //小的算法，两种情况都满足，需要用到取模的思想
        return (rear+1)%MaxSize == front;
    }
    public boolean isEmpty(){
        //此时不变
        return front==rear;
    }
    public void add(int num){
        if(isFull()){
            throw new RuntimeException("队列已满");
        }
        arr[rear] = num;
        //此时用到方法，队列不能容量达到数组的最大容量，rear指向最后Maxsize-1的时候，如果前面
        //有位置的话，会直接跳到第一个
        rear = (rear+1)%MaxSize;
    }
    public int get(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        int num = arr[front];
        front = (front+1)%MaxSize;
        return num;
    }
    public void show(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        //涉及到一个细节算法，就是考察队列的有效值的个数，同时这也是常见的小思路
        //相当于扩大了虚拟容量
        for(int i=front;i<front+size();i=(i+1)%MaxSize){
            System.out.println(arr[i]);
        }
    }
    public int size(){
        return (rear-front+MaxSize)%MaxSize;//队尾减队头，否则为负数
    }
    public int head(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return front;//队头的元素就是front
    }
}

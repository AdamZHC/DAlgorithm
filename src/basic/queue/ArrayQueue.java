package basic.queue;

public class ArrayQueue<I extends Number> {
    public static void main(String[] args){

    }
}
class QueueArray{
    private int MaxSize;
    private int front ;
    private int rear  ;
    private int[] arr;
    public QueueArray(int max){
        MaxSize=max;
        arr = new int[MaxSize];
        front=-1;
        rear=-1;
    }
    public boolean isFull(){
        return rear == MaxSize-1;
    }
    public boolean isEmpty(){
        return front==rear;
    }
    public void add(int num){
        if(isFull()){
            throw new RuntimeException("队列已满");
        }
        arr[++rear] = num;
    }
    public int get(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[++front];
    }
    public void show(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }//也就是rear+1
        for(int i=front+1;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }
    public int head(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return front+1;
    }
}

package basic.pointoffer;

import java.util.Stack;

public class Offer9 {
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.append(1);
        System.out.println(queue.remove());
        queue.append(3);
        queue.append(2);
        System.out.println(queue.remove());
        queue.append(5);
        queue.append(4);
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
    }
}
class Queue <T> {
    //假设此时无限容量
    private Stack<T> in = new Stack<>();
    private Stack<T> out = new Stack<>();
    public Queue(){

    }
    //肯定是一个一个入队
    public void append(T element){
        if(in.isEmpty()){
            while(!out.isEmpty()){
                in.push(out.pop());
            }
        }
        in.add(element);
    }
    public T remove(){
        if(in.isEmpty() && out.isEmpty()){
            System.out.println("队列已空");
            return null;
        }else{
            if(out.isEmpty()){
                while(!in.isEmpty()){
                    out.push(in.pop());
                }
            }
            return out.pop();
        }
    }
}

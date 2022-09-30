package basic.pointoffer;
import java.util.ArrayDeque;
import java.util.Queue;
public class ManiStack {
}
class Stack<T>{
    private Queue<T>  circle1 = new ArrayDeque<>();
    private Queue<T>  circle2 = new ArrayDeque<>();
    public Stack(){

    }
    public void push(T element){
        if(!circle1.isEmpty()){
            circle1.add(element);
        }else{
            circle2.add(element);
        }
    }
    public T pop(){
        if(!circle2.isEmpty()){
            while(circle1.size() != 1){
                circle2.add(circle1.remove());
            }
            return circle1.remove();
        }else{
            while(circle2.size() != 1){
                circle1.add(circle2.remove());
            }
            return circle2.remove();
        }


    }
}
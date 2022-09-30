package codes.stack;

public class LinkedStack {
    //用单链表的思想模拟栈就是利用头插法
    public static void main(String[] args){
        LStack stack = new LStack();
        stack.push(new Node (9));
        stack.push(new Node(0));
        stack.push(new Node (1));
        stack.push(new Node (3));
        stack.push(new Node (5));
        for(int i =0;i<5;i++){
            System.out.println(""+stack.pop());
        }
    }
}
class Node{
    int num;
    Node next;
    public Node(int num){
        this.num = num;
        //节点的构造方法不需要赋值下一个，否则那就不是真正节点了
    }
}
class LStack{
    Node head;
    public LStack (){
        head = new Node(0);
    }
    public void push(Node node){
        node.next = head.next;
        head.next = node;
    }//是实际加了一个节点，相同的一个对象，而不是单单添加值什么的
    public int pop(){
        if(head.next == null){
            System.out.println("栈空");
            return 0;
        }
        //如果只有一个有效节点，结果就是这样的，不能指向后面
        if(head.next.next == null){
            int num = head.next.num;
            head.next = null;
            return num;
        }
        int num = head.next.num;
        head.next = head.next.next;
        return num;
    }
}

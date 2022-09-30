package review;

public class MLinkedStack {
    //链式栈通常不带头结点
    //标记是否为空
    boolean flag = true;
    //就表示指向堆顶元素的指针
    Node p;

    public MLinkedStack() {

    }

    public int pop() {
        if(flag){
            System.out.println("empty");
            return 0x80000000;
        }
        int ans = p.data;
        p = p.next;
        flag = p == null;
        return ans;
    }

    public void push(int element) {
        Node e = new Node(element);
        e.next = p;
        p = e;
    }
}

package review;

public class MLinkedList {
    Node head;
    int p;

    public MLinkedList() {
        head = new Node(0);
        p = 0;
    }
    //增加
    public void insert(int idx, int element) {
        if(!check(idx)) {
            System.out.println("wrong idx");
            return;
        }
        Node temp = head;
        for(int i = 0; i < idx; ++i)
            temp = temp.next;
        Node insertOne = new Node(element);
        insertOne.next = temp.next;
        temp.next = insertOne;
        p++;
    }
    //删除
    public void delete(int idx) {
        if(!check(idx)){
            System.out.println("wrong idx");
            return;
        }
        Node temp = head;
        for(int i = 0; i < idx; ++i)
            temp = temp.next;
        temp.next = temp.next.next;
        p--;
    }
    //修改
    public void update(int idx, int element) {
        if(!check(idx)) {
            System.out.println("wrong idx");
            return;
        }
        Node temp = head;
        for(int i = 0; i <= idx; ++i)
            temp = temp.next;
        temp.data = element;
    }
    //查询
    public int query(int idx) {
        if(!check(idx)) {
            System.out.println("wrong idx");
            return 0x80000000;
        }
        Node temp = head;
        for(int i = 0; i <= idx; ++i)
            temp = temp.next;
        return temp.data;
    }
    public boolean check(int idx) {
        return idx < p;
    }

}

class Node{

    int data;

    Node next;

    public Node(int data) {
        this.data = data;
    }

}


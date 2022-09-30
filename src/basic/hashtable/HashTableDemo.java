package basic.hashtable;

public class HashTableDemo {
}
class Node{
    public int id;
    public String data;
    Node next;
    public Node(int id, String data){
        this.id = id;
        this.data = data;
    }
    public String toString(){
        return "id="+id+"\n"+"data="+data;
    }
    //正常的思路就是不需要怎么理解'.'也就是转化到C语言中的指向，就按照正常的思路来就可以，但是
    //如果是问题到链表的底层实现原理就要去理解指向，如果在平时的问题中也去理解的话，那就不太好了
    //是不需要的
}
class LinkedList{
    //这里的头指针里面没有数据
    //不会把底层封装的head的变量显示地调用出来
    //借助于这个head完成一系列的操作
    //一般在用户层就是定义一个引用变量然后再使用方法
    //和C语言中的链表的定义方法其实是一样的，在C语言中有单独指向的结构体的指针和就是结构体的两个
    //不同的类型，这里就把指针封装到LinkedList类里面，思路是一样的，也就是那个定义两个结构体，
    //这里定义了两个类 -- 基本理解到这个部分就可以了
    //这里核心是引用数组又，也就是说每一个里面是一个指针变量，因为在数组里初始化，所以就是
    //初始值为null这时需要赋值
    Node head;
    public LinkedList(){
        head = new Node( -1,"");
    }
    public void add(Node node){
        Node temp= head;
        while(head.next != null){
            temp = temp.next;
        }
        temp.next = temp;
    }
    public void update( int id, String data){
        if(head.next == null){
            return;
        }
        Node temp = head.next;
        boolean flag = false;
        while(true){
             if(temp == null){
                 break;
             }
             if(temp.id == id){
                 flag = true;
                 break;
             }
             temp = temp.next;
        }
        if(flag == true){
            temp.data = data;
        }else{
            return;
        }
    }
    //这里有一个修改的方法和查询具体某个人员信息的代码是一样的 --因此就不具体列出来
    public void list(){
        if(head.next == null){
            return;
        }
        Node temp = head.next;
        while(temp != null){
            System.out.println(temp.toString());
        }
    }

}
class HashTable{
    LinkedList[] linkedListArray;
    int size;
    //注意构造方法的传参hashtable的规模，也就是hashtable中链表个数
    public HashTable(int size){
        linkedListArray = new LinkedList[size];
        this.size = size;
        for(int i = 0;i<size;i++){
            linkedListArray[i] = new LinkedList();
        }
    }
    public void add(Node node){
        linkedListArray[func(node.id)].add(node);
    }
    //通过id获取具体的哈希表中的链表索引，大致上就是这样的思路
    //似乎写着写着就有思路了
    public void update(int id, String data){
        linkedListArray[func(id)].update(id,data);
    }
    //注意这个哈希散列函数是很关键的思想，否则就和对象数组就一样了，但实际上是不一样的，
    //通过这个id唯一确定的数字以及哈希散列函数来确定在HashTable中在哪个链表，然后在
    //这就到了链表中了，到了链表中就好说了，通过在链表中封装的函数和Id来确定具体的
    //也就是HashTable中寻找是用的哈希散列函数，得到链表->在链表中再去寻找
    public int func(int id){
        return id % size;
    }
    public void list(){
        for(LinkedList i :linkedListArray){
            i.list();
        }
        return;
    }
}
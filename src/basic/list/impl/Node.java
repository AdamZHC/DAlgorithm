package basic.list.impl;

class Node <T>{
    int no;
    T name;
    T nickName;
    Node<T> next;
    //这里将引用对象理解成指针比较好，链表的根本思想还是指针，也就是引用对象
    //在后面定义的Linkedlist也是最前面声明有一个头节点，虽然没有说明，但实际上就是头指针
    //构造方法
    public Node(int no, T name, T nickName){
        this.no=no;
        this.name=name;
        this.nickName=nickName;
    }
    public Node(int no){
        this.no=no;
    }
    @Override
    public String toString(){
        return "no:"+no+"\nname:"+name+"\nnickName:"+nickName;
    }
}
//这类题的思路还是要判断空
//限制参数的范围
//因为往往要循环，所以还要考虑辅助节点定在head还是head.next
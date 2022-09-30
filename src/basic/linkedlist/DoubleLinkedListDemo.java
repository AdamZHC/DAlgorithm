package basic.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args){
        DoubleLinkedList college = new DoubleLinkedList();
        college.add(new DoubleNode(1,"哈尔滨工业大学","哈工大"));
        college.add(new DoubleNode(2,"北京理工大学","北理"));
        college.list();
        college.addByOrder(new DoubleNode(6,"武汉大学","武大"));
        college.list();
    }
}
class DoubleNode {
    int no;
    String name;
    String nickName;
    //默认为null
    DoubleNode pre;
    DoubleNode next;
    //双向链表有标志前一个节点和后一个节点
    public DoubleNode(int no,String name,String nickName){
        this.no=no;
        this.name=name;
        this.nickName=nickName;
    }
    @Override
    public String toString(){
        return "no:"+no+"\nname:"+name+"\nnickName:"+nickName;
    }
}
class DoubleLinkedList {
    //头指针都是一样的，直接初始化
    //只有一个成员变量
    private DoubleNode head = new DoubleNode(0, "", "");

    //直接在最后面添加节点
    public void add(DoubleNode node) {
        //思路就是借助于头节点添加节点
        //此时不需要判断为空
        //而且直接加到后面，和单链表是一样的
        //也需要遍历到最后
        DoubleNode temp = head;
        //死循环一直到链表的尾部
        //不需要加flag，因为完成操作怎样都可以
        while (true) {
            if (temp.next == null) {
                //temp指到链表的最后跳出
                break;
            }
            //不是最后的话，往后移
            temp = temp.next;
        }
        //最后赋值添加节点
        //只在这里特殊，也就是说
        temp.next = node;
        node.pre = temp;
    }
    public void addByOrder(DoubleNode node){
        //前插由于最后有可能到最后的一个指针为null，所以还是最好用后插
        DoubleNode temp =head;
        //小技巧，设置一个相同的表示是否含有相同编号的小技巧
        boolean flag = false;
        while(true){
            if(temp.next==null){
                break;
            }
            if(temp.next.no>node.no){
                break;
            }
            if(temp.next.no==node.no){
                flag = true;
                //throw new RuntimeException("链表中含有该编号");
            }
            temp=temp.next;
        }
        if(flag){
            throw new RuntimeException("链表中含有该编号");
        }else{
            if (temp.next!= null) {
                temp.next.pre=node;
                node.next =temp;
                temp.next = node;
                node.pre = temp;
            }else{
                temp.next=node;
                node.pre=temp.pre;
            }
        }
    }
    public void update(DoubleNode node) {
        DoubleNode temp = head.next;
        //判断为空
        //与单链表是一样的
        if (temp.next == null) {
            System.out.println("链表为空");
        }
        boolean flag = false;
        while (true) {
            if (temp == null) {
                System.out.println("没有该节点");
                break;
            }
            if (temp.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = node.name;
            temp.nickName = node.nickName;
        } else {
        }
    }

    public void del(int no) {
        //此时就是比较特殊，直接找到对应节点，也就是说
        //这是可以使辅助节点落在与数据直接交互的位置
        //注意的点就是在于前有序，后面有可能到最后一个部分，若此时还用后者的条件大于待比较值的时候，就会出问题
        //这也是一样的如果说该删除的节点是最后一个结点的话，那么一般的删除方法是不可以行的
        if (head.next==null) {
            throw new RuntimeException("链表为空");
        }
        DoubleNode temp = head.next;
        boolean flag = false;
        //同样的思路，找到是否可以删除的节点
        while (true) {
            if (temp == null) {//因为较之前的删除往前一个，所以此时就是跳出的条件就是temp==null
                //如果使temp.next的话，可能会剩下一个尾节点
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            if(temp.next==null){
                //这就是前有序的细节问题，需要注意的地方
                temp.next.pre=temp.pre;
            }
        } else {
            throw new RuntimeException("找不到该节点");
        }
    }

    public void list() {
        if (head.next==null) {
            throw new RuntimeException("链表为空");
        }
        DoubleNode temp = head.next;
        //思想同构，这里应该直接指向head.next，因为需要遍历，不需要在head里面操作
        while (true) {
            //如果temp为null的时候，这时已经到最后一个节点的指针域了，temp.no已经无意义了
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
    public void reverseList(){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        DoubleNode temp = head.next;
        //遍历到最后一个
        while(temp.next!=null){
            temp=temp.next;
        }
        //遍历到头节点就结束
        while(temp.pre!=null){
            System.out.println(""+temp);
            temp=temp.pre;
        }
    }
}

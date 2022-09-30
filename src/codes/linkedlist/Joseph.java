package codes.linkedlist;

public class Joseph {
    public static void main (String[] args){
        CLinkedList.solution(6,3);
    }
}
class CNode{
    int num;
    CNode next;
    int password;
    public CNode(int num){
        this.num = num;
        //构造方法可不能决定给它直接添加一个next节点
    }
}
class CLinkedList{
    CNode head = new CNode(0);
    //环形链表添加一般统一添加足够的数量再闭环
    //否则每次添加都闭环，下一次添加死循环找不到尾部，或者是确定的数字添加
    //老师的添加思路也是一个一个添加，但是不是传统的判断方法
    public void add(CNode node){
        //不需要判空
        //选择起始点在head，先执行到temp.next==null
        //之后再连接和首尾连接
        CNode temp = head;
        while(temp.next!=null){
            temp=temp.next;
        }
        temp.next=node;
    }
    public void del(CNode node){
        node.next = node.next.next;
    }
    public void pre(int size){
        for(int i = 1 ; i <= size; i ++){
            this.add(new CNode(i));
        }
        CNode temp = head;
        while(temp.next!=null){
            temp=temp.next;
        }
        temp.next=head.next;
    }
    public void show(int size){
        //由于是show，起始位置应当在head.next
        if(head.next==null){
            System.out.println("链表为空");
        }
        CNode temp = head.next;
        //循环结束就在temp==null,需要往后推一位
        for(int i=0; i<size ;i++){
            System.out.println(""+temp.num);
            temp=temp.next;
        }
    }
    CNode getHeadNode(){
        return head;
    }

    public static void solution(int size,int step){
        CLinkedList josephu = new CLinkedList();
        if(size==0){
            return;
        }
        if(step==1){
            josephu.show(size);
        }
        josephu.pre(size);
        CNode temp = josephu.getHeadNode();
        while(temp != temp.next){
            for(int i = 0 ; i < step-1 ;i ++){
                temp = temp.next;
            }
            System.out.println(""+temp.next.num);
            josephu.del(temp);
        }
        System.out.println(temp.next.num);
    }
}

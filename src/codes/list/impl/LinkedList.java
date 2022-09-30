package codes.list.impl;

import codes.list.MyList;

class LinkedList<T> implements MyList<Node<T>> {
    //头指针都是一样的，直接初始化
    //只有一个成员变量
    private  Node<T> head = new  Node(0);
    //直接在最后面添加节点
    @Override
    public void add( Node<T> node) throws RuntimeException{
        //思路就是借助于头节点添加节点
        //在借助于一个临时变量来存储，这里有两个选择，一个是头节点，还有一个就是头节点的后面一个
        //也就是后面的next，应当从head开始，从head开始因为需要添加，直接temp.next=nodeNew
        //如果是head.next的话，有可能直接就是为null，没有办法直接添加到这个对象里面
        //包括初始化和后面循环判断条件就是一个思路应当是temp.next判断
         Node temp = head;
        //死循环一直到链表的尾部
        while(true){
            if(temp.next==null){
                //temp指到链表的最后跳出
                break;
            }
            //不是最后的话，往后移
            temp=temp.next;
        }
        //最后赋值添加节点
        temp.next=node;
    }
    public void add( Node<T> node ,int idx) {
        //思路就是借助于头节点添加节点
        //在借助于一个临时变量来存储，这里有两个选择，一个是头节点，还有一个就是头节点的后面一个
        //也就是后面的next，应当从head开始，从head开始因为需要添加，直接temp.next=nodeNew
        //如果是head.next的话，有可能直接就是为null，没有办法直接添加到这个对象里面
        //包括初始化和后面循环判断条件就是一个思路应当是temp.next判断
        Node temp = head;
        //死循环一直到链表的尾部
        int cnt = 0;
        while(cnt < idx){
            if(temp.next==null){
                //temp指到链表的最后跳出
                break;
            }
            //不是最后的话，往后移
            temp=temp.next;
        }
        //最后赋值添加节点
        temp.next=node;
    }
    public void addByOrder( Node<T> node){
         //思路就是：
        //1.通过一个temp指针遍历找到应该插入的位置，注意由于单链表指针指向的位置应该在插入位置之前的节点
        //2.之后把temp.next赋给新的节点.next
        //3.最后把新的节点赋给temp.next
        //注意相同编号的是不能填入的
         Node temp =head;
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
            node.next = temp.next;
            temp.next = node;
        }
    }
    @Override
    public void update( int idx,Node<T> node){
        Node temp = head.next;
        if(temp.next==null){
            System.out.println("链表为空");
        }
        boolean flag = false;
        int cnt = 0;
        while(cnt < idx){
            if (temp == null) {
                System.out.println("没有该节点");
                break;
            }
            if(temp.no==node.no) {
                flag=true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.name= node.name;
            temp.nickName=node.nickName;
        }else{
        }
        //由该题得到的思路：
        //单链表中的操作实现如下：
        //1.是否判空isEmpty
        //2.是否找到目标节点(flag),修改或者增添的节点
        //3.最后根据flag加一个if-else选择语句来决定是否可以实现该操作
        //注意事项：实现添加的时候，初始化辅助节点temp是在赋为head因为要添加，要在之前添加
        //可以不直接接触数据，但是修改和展示的时候需要直接接触数据，由于head里面没有数据，所以
        //只需要赋为head.next,一开始都是对temp本身赋值，这样比较方便一点
        //flag可标记需要操作的，也可以标记特殊的
        //寻找的过程就是通过while循环
    }

    @Override
    public Node<T> query(int idx) {
        Node temp = head.next;
        if(temp.next==null){
            System.out.println("链表为空");
        }
        int cnt = 0;
        while(cnt < idx){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        return temp;
    }

    public void delete(int no){
        if(isEmpty()){
            throw new RuntimeException("链表为空");
        }
         Node temp = head;
        boolean flag = false;
        //同样的思路，找到是否可以删除的节点
        while(true){
            if(temp.next==null){
                break;
            }
            if(temp.next.no == no){
                flag = true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            temp.next = temp.next.next;
        }else{
            throw new RuntimeException("找不到该节点");
        }
    }
    public void delete(){
        if(isEmpty()){
            throw new RuntimeException("链表为空");
        }
        Node temp = head;
        boolean flag = false;
        //同样的思路，找到是否可以删除的节点
        while(true){
            if(temp.next==null){
                break;
            }
            temp=temp.next;
        }
        if(flag){
            temp.next = temp.next.next;
        }else{
            throw new RuntimeException("找不到该节点");
        }
    }
    public void list(){
        if(isEmpty()){
            throw new RuntimeException("链表为空");
        }
         Node temp = head.next;
        //思想同构，这里应该直接指向head.next，因为需要遍历，不需要在head里面操作
        while(true){
            //如果temp为null的时候，这时已经到最后一个节点的指针域了，temp.no已经无意义了
            if(temp==null){
                break;
            }
            System.out.println(temp);
            temp=temp.next;
        }

    }
    public  Node getHeadNode() {
        return head;
    }
    //对于定义在某类里面的静态方法来说，该类不能直接调用非静态成员一般的处理方法就是把该对象作为参数传入该方法中
    public static int getLength( Node head){
        //思路就是遍历从而获得该链表的有效值个数
        //判断是否为空
        if(head.next==null){
            return 0;
        }
        //降低程序的耦合度
        //计数器
        int length=0;
         Node cur = head.next;//不需要前插和删除，而且直接接触数据应当放在head.next
        while(cur!=null){
            length++;
            cur = cur.next;
        }
        return length;
    }
    public static  Node lastIndexNode( Node head, int index){
        //思路比较简单，就是获取长度之后遍历到size-index个节点
        if(head.next==null){
            return null;
        }
        int size = getLength(head);
        if(index <=0 || index>size){
            return null;
        }
        //辅助节点
         Node cur = head.next;
        for(int i=0; i<size-index; i++){
               cur=cur.next;
        }
        return cur;
    }
    //注意传参都是头节点，也就是头指针
    public static void reverse ( Node head){
        //反转链表的思路，就是一个一个遍历然后用头插法插入
        //这里强调的就是头插法的使用
        //细节问题就是要判断链表的长度来决定是否需要反转
        if(head.next==null||head.next.next==null){
            return;
        }
         Node reverseHead = new  Node(0,"","");
        //两个辅助变量
        //因为不是在本链表的操作，把cur后一个删除保证的是链表的不断，该操作直接把cur的节点拆下来，为了操作方便，
        //这样的就是把cur指向要删除的节点，也就是cur = head.next;
         Node cur = head.next;
         Node next=cur.next;
        //遍历就是while循环
        while (cur!=null){//前面是head.next这个就是null
            //头插法
            next=cur.next;
            cur.next=reverseHead.next;
            reverseHead.next=cur;
            cur=next;
        }
        //java里面对象名就是指向实际在堆内存中的指针，对象变量名就是指向该节点的指针
        head.next=reverseHead.next;
    }
    @Override
    public int size(){
        Node temp = this.getHeadNode();
        int cnt = 0;
        while(temp.next == null){
            temp = temp.next;
            cnt ++;
        }
        return cnt;
    }
    @Override
    public boolean isFull(){
        return false;
    }
    @Override
    public boolean isEmpty(){
        //空就是指head后面没有指向的节点
        //引用变量就是指向该实际对象的指针
        return head.next == null?true:false;
    }
}
//这类题的思路还是要判断空
//限制参数的范围
//因为往往要循环，所以还要考虑辅助节点定在head还是head.next
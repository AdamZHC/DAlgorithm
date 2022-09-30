package basic.tree;

public class SegmentTreeDemo{
    /**
     * 有这样两种设计思路:
     * 1.把所有的实现都放到SNode类中，这样的话理解比较简单，树完全空壳，就是调用结点的方法的媒介，比较符合常规思路。
     * 2.把所有的实现都都放到Tree类中，这样的话应当都用静态方法，Tree相当于一个工具类
     * @param args
     */
    public static void main(String[] args){
        int a[] = {1,3,5,7,9,11};
        SegmentTree tree = new SegmentTree(a);
        SNode root = tree.root;
        SegmentTree.update(root,0,2,2);
        SegmentTree.traverse(root);
        System.out.println(SegmentTree.query(2,root));
        SegmentTree.traverse(root);
    }
}
/**
 * 关于线段树的操作
 * 有一个数组对应的线段树是固定的，根节点和叶节点等的值是固定的，但是怎样来存又是一个新的问题
 * 在视频中选择用数组来存，也就是顺序存储二叉树
 */
class SegmentTree {
    SNode root = new SNode();
    //无参构造方法不用管，相当于一个空的线段树
    //递归构造线段树（先递归建树）
    public SegmentTree(int[] arr){
        new SNode(arr).recurseCreate(root,0,arr.length-1);
    }
    public static void traverse(SNode node){
        System.out.println("left:"+node.left+" right:"+node.right+" sum:"+node.sum+" tag:" + node.tag);
        if(node.leftNode != null){
            traverse( node.leftNode);
        }
        if (node.rightNode != null){
            traverse(node.rightNode);
        }
    }
    //现在的情况就是包含了mid = (left + right) / 2
    public static void update(int index, int value ,SNode node){
        if(node.left == node.right){
            node.sum = value;
            return;
        }
        if(index <= (node.left + node.right) / 2){
            update(index,value,node.leftNode);
        }else{
            update(index,value,node.rightNode);
        }
        //注意这里递归完成之后要重新赋值
        node.sum = node.leftNode.sum + node.rightNode.sum;
    }
    public static void update(SNode node, int L , int R , int value){
        //区间修改，需要懒惰标记
        //线段树需要画线段
        if(L == node.left && R == node.right){
            node.sum += (node.right - node.left + 1) * value;
            node.tag = value;
            return;
        }
        if((node.right + node.left) / 2 >= R){
            update(node.leftNode, L , R, value);
        }else if(L >= (node.right + node.left) / 2 + 1){
            update(node.rightNode,L,R,value);
        }else{
            update(node.leftNode,L,(node.right + node.left) / 2,value);
            update(node.rightNode,(node.right + node.left) / 2 + 1, R, value);
        }
        node.sum = node.leftNode.sum + node.rightNode.sum;

    }
    public static int query(int L, int R ,SNode node){
        if(L == node.left && R== node.right){
            return node.sum;
        }
        if(L >= node.left && R <= (node.right + node.left) / 2){
             return query(L, R, node.leftNode);
        }else if(L >= (node.right + node.left) / 2 + 1 &&  R <= node.right){
            return query(L,R,node.rightNode);
        }else{
            return  query(L,(node.right + node.left) / 2,node.leftNode ) + query((node.right + node.left) / 2 + 1,R,node.rightNode );
        }
        //注意这里递归完成之后要重新赋值
    }
    public static int query(int index, SNode node){
        if(node.left == node.right ){
            return node.sum;
        }
        //push down在这里处理就可以，所有的叶子节点和非叶子结点都处理了，如果在上面判断那就冗余了，
        //因为下面的node.leftNode.sum += (node.leftNode.right - node.leftNode.left + 1) * node.tag;
        //可以包括叶子结点的处理
        if(node.tag != 0){
            node.leftNode.tag = node.tag;
            node.leftNode.sum += (node.leftNode.right - node.leftNode.left + 1) * node.tag;
            node.rightNode.tag = node.tag;
            node.rightNode.sum += (node.rightNode.right - node.rightNode.left + 1) * node.tag;
            node.tag = 0;
        }
        if(index <= (node.right + node.left) / 2){
             return query(index,node.leftNode);
        }else{
             return query(index,node.rightNode);
        }
    }
}

/**
 * 关于线段树结点的声明
 * 它的初始化方法是提前已经有声明了，我这样会报空指针异常
 */
class SNode{
    SNode leftNode;
    SNode rightNode;
    int left;
    int right;
    int sum;
    int tag = 0;
    int [] arr;
    public SNode(){}
    public SNode(int[] arr){
        this.arr = arr;
    }
    public SNode(int left, int right, int sum){
        this.left = left;
        this.right = right;
        this.sum = sum;
    }
    public void recurseCreate(SNode node,int left, int right){
        //退出条件
        //由单个函数体内的思路来说
        node.left = left;
        node.right = right;
        if (left == right){
            node.sum = arr[left];
            return;
        }
        node.leftNode  = new SNode();
        node.rightNode = new SNode();
        //问题就在于不能把每个点分开
        recurseCreate(node.leftNode, left , (left + right)/2 );
        recurseCreate(node.rightNode,(left + right) / 2 + 1, right);
        node.sum = node.leftNode.sum + node.rightNode.sum;
    }
}

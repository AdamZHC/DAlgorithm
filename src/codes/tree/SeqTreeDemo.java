package codes.tree;

public class SeqTreeDemo {
    //顺序存储二叉树的特点
    /*
    * 1.存储的树结构必须是完全二叉树
    * 2.第n个元素的左节点是第2*n+1个元素，右结点是2*n+2个元素
    * 3.第n个元素的父结点是第(n-1)/2个结点
    * 注意：上述提到的第n个中的n是指在数组中的元素个数
    * 顺序存储就是一层一层地存储，其实就是存在队列里，从左到右，记住，顺序存储就是从左到右一层一层按顺序存储
    * */
    public static void main(String[] args){
        SeqTree tree = new SeqTree(7);
        tree.pre(0);
        SeqTree.pre(tree.arr,0);
    }
}
class SeqTree{
    public int[] arr;
    //顺序存储二叉树
    public SeqTree(int size){
        arr = new int[size];
        for(int i = 0;i < size;i++){
            arr[i]  = i+1;
        }
    }
    //根据顺序存储的特点来遍历二叉树
    //第n个结点的左子节点是第2*n+1，右子节点是2*n+2
    //第n个结点的父节点是(n-1)/2，其实就是根据具体的传参来遍历
    //前序遍历 -- 其实就三步，就是打印，判断是否为空递归，判断是否为空递归
    //注意细节--也就是如果为空的话，就必须判断一下
    public static void pre(int[] arr, int n){
        //需要有跳出条件，其实就是递归到最后了，其实那个跳出条件加不加都可以，因为没有特别大的必要，后面的条件总会帮助把
        //需求实现，根本用不着判断
        //树还是那个树，顺序存储的过程还是不变，就是打印的语句位置不同
        //其实这两个没有什么关系，也就是说前面是顺序存储，后面不涉及到顺序存储，只涉及到空结点的指向根据不同的遍历顺序
        if(arr == null || n>arr.length-1){
            return;
        }
        System.out.println(arr[n]);
        if( n*2+1 <= arr.length-1){
            pre(arr,2*n+1);
        }
        if( n*2+2 <= arr.length-1){
            pre(arr,2*n+2);
        }
    }
    public void pre (int n){
        if(arr == null){
            return;
        }
        System.out.println(arr[n]);
        if( n*2+1 <= arr.length-1){
            pre(2*n+1);
        }
        if( n*2+2 <= arr.length-1){
            pre(2*n+2);
        }
    }
}

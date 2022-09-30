package codes.tree.avltree;
//把生成二叉排序树的包导入
//ctrl+F  ctrl+R
//平衡二叉树的特点就是任何一个结点的左子树和右子树的高度差的绝对值不超过1
//关键逻辑：
/*
* 1.每插入一个结点，都需要对整个二叉树平衡调整,也就是我说的传入一个root，对整个二叉树进行平衡化调整
* 如果不是每次插一个结点都进行，那必然需要进行递归调整，但是如果是每次都插入的话，是否需要递归平衡化也是未知的
* 2.注意递归的每次都要往下判断是否需要调整
* 3.最小失衡树：离插入结点最近且以BF>1的结点为根节点的子树，每次只需要找到最小不平衡子树即可，
* 也就是说每次只需要在最小不平衡子树上面下功夫来旋转就好了
* */
public class AVLTreeDemo {
    public static void main(String[] args){
        int[] arr = {4,3,6,5,7,8,9};
        Node root = sorted(arr);
        leftRotate(root);
        traverse(root);
        System.out.println(height(root));
    }
    //确定某个结点为根节点的高度可以非常简便
    //函数体的确定过程有一定逻辑过程，另外递归的退出条件也是非常简单
    //注意对其进行双旋转
    public static int height(Node root){
        return Math.max( root.left == null ? 0:height(root.left),root.right == null ? 0:height(root.right))+1;
    }
    public static void leftRotate(Node root){
        /*
        * 左旋转的思路要记住
        * 1.首先开辟一个新结点，结点的值等于当前结点，左节点指向当前结点的左子结点，右结点指向当前结点的右子结点的左子结点
        * 2.让当前结点的值等于其右子结点的值
        * 3.最终让当前结点的左结点指向新结点，右结点指向右结点的右结点
        * */
        //1.首先开辟一个新结点，结点的值等于当前结点，左节点指向当前结点的左子结点，右结点指向当前结点的右子结点的左子结点
        Node node = new Node(root.id);
        node.left = root.left;
        node.right = root.right.left;
        //2.让当前结点的值等于其右子结点的值
        root.id = root.right.id;
        //3.最终让当前结点的左结点指向新结点，右结点指向右结点的右结点
        root.left = node;
        root.right = root.right.right;
    }
    public static void rightRotate(Node root){
        /*
         * 右旋转的思路要记住:
         * 1.首先开辟一个新结点，结点的值等于当前结点，左节点指向当前结点的左子结点的右子结点，右结点指向当前结点的右子结点
         * 2.让当前结点的值等于其左子结点的值
         * 3.最终让当前结点的右结点指向新结点，左结点指向左结点的左结点
         * */
        //1.首先开辟一个新结点，结点的值等于当前结点，左节点指向当前结点的左子结点的右子结点，右结点指向当前结点的右子结点
        Node node = new Node(root.id);
        node.left = root.left.right;
        node.right = root.right;
        //2.让当前结点的值等于其左子结点的值
        root.id = root.left.id;
        //3.最终让当前结点的右结点指向新结点，左结点指向左结点的左结点
        root.right = node;
        root.left = root.left.left;
    }
    public static void traverse(Node node){
        if(node.left != null){
            traverse(node.left);
        }
        System.out.println(node.id);
        if(node.right != null){
            traverse(node.right);
        }
    }
    public static Node sorted(int[] arr) {
        Node root = new  Node(arr[0]);
        Node node = null;
        Node temp = null;
        for (int i = 1; i < arr.length; i++) {
            //先把这个树创建出来
            node = new  Node(arr[i]);
            temp = root;
            while (true) {
                //如果辅助结点到了null说明二叉树查找到最后了
                if (temp == null){
                    break;
                }
                //如果待插入结点小于辅助待比较的话，找到右结点
                if(node.id < temp.id){
                    //如果为空的话，那就添加上，无论是否都要填入
                    if(temp.left == null){
                        temp.left = node;
                        temp = node;
                    }
                    temp = temp.left;
                }else{
                    if(temp.right == null){
                        temp.right = node;
                        temp = node;
                    }
                    temp = temp.right;
                }
            }
        }
        return root;
    }
}
class Node {
    int id;
    Node left;
    Node right;
    public Node(int id){
        this.id = id;//这个赋值也能错也是够可以的
    }
    public String toString(){
        return id+"";
    }
}
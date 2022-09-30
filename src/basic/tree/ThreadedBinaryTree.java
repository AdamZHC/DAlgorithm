package basic.tree;

public class ThreadedBinaryTree {
    //线索化二叉树就是中序线索化二叉树
    //应当有这样的思想，因为遍历二叉树的过程是不存在先判断的，如果有先判断的情况，那么就不需要后面遍历外层的判断
    //如果是先不判断的话那就后判断，我认为采取这样的原因一个是为了更方便，另一个就是为了封装更科学
    //我懂了，线索化应该也分前中后序，不然没法处理
    public static void Threading(Node node,Node pre){
        if(node == null){
            return;
        }
        //先线索化左子树
        Threading(node.left,pre);
        //如果左子节点为空的话，说明是一个应该指向前驱结点
        if(node.left == null){
            node.leftType = 1;
            node.left = node.pre;
        }
        //处理后继结点是后来处理的
        //像是在构建双向链表根据前者来指向
        if(pre!= null && pre.right == null){//上下都需要是待插入的结点为空才可以，如果本来指向右子结点，
            //而右子结点又是后继结点的话，这时的type仍是0
            pre.right = node;
            pre.rightType =1;
        }
        //1.本身就是pre node就是前后的关系
        //2.根据中序线索化的特性，只有到了中间的时候才会赋值，也就是说到了中间的时候node.left和node.right中间的位置
        //以及把pre = node放在中间可以共同决定node.right的前一个结点一定是node,因为中间就是这样的，意思是，操作完左子树的
        //结点也就是之后，node一定是node.right的前驱结点
        pre = node;
        Threading(node.right,pre);
        //其实核心遍历二叉树的语句都没有变
        //核心还是二叉树遍历的路线
    }
    public static void preTraverse(Node root){//非递归的
        if(root == null){
            return;
        }
        Node node = root;
        while(node != null){
            while(node.leftType == 0){
                node = node.left;
            }
            System.out.println(node.id);
        }
    }
}

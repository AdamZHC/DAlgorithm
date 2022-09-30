package basic.tree;
//因为一旦有符合的值就会立即返回，那么这个递归函数就会回溯这样的话，有的话立即结束，不会再次判断，所以就是四个结构(不正确)
//上面是线性的思路，这时的结构是回溯性质的非线性的要转换思路，从长驱直入转化到懂得用temp来保存，完成非线性的任务来实现
//也就是说递归要开发思路到非线性的，其特点就是用temp
//这样多分叉的结构需要用一个临时变量存起来，这是很重要的，考虑不够完全
//注意在韩老师的思路中是指要在Node中核心代码，在Tree中封装代码
//和我写的结构是不一样的
public class TraverseDemo {
    public static void main(String[] args){
        Tree tree = new Tree();
        System.out.println(tree.delete(tree.getRoot(),18));
        tree.Traverse("pre");
        //System.out.println(basic.tree.preSearch(basic.tree.getRoot(),2));
    }
}
//和链表的数据结构是一个样的，结点的定义是一个类，含有基本的逻辑关系
//数据结构的是一个类，对应的具体的唯一标识这个数据结构的结构就是上面的一个类
//这样的思路最好，就是有一个结点类，还有一个具体的数据结构类，也就是树类
class Tree{
    //没事，没有一下子想出来没事
    Node root;//对象就是指向该类对应开辟内存的部分的指针，root == null那么就是空树
    public Tree(){
        //先赋值获得堆内存，可以先不给后面的结点获取值
        root= new Node(0);//覆盖掉了
        root.left = new Node(1);
        root.right = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.right.left = new Node(5);
        root.right.right = new Node(6);
    }
    //前序遍历
    public void Traverse(String flag){
        switch (flag){
            case "pre" : preTraverse(root); break;
            case "infix" :infixTraverse(root); break;
            case "post" :postTraverse(root); break;
        }
    }
    //前序遍历
    public void preTraverse(Node node){
        System.out.println(node.id);
        if(node.left != null){
            preTraverse(node.left);
        }
        if(node.right != null){
            preTraverse(node.right);
        }
    }
    //前序查找 -- 传的时候是root但是没办法再递归的方法体内体现出来
    public Node preSearch(Node node,int id){
        //如果在一个子树里面没有的话始终会传回一个null所以这样的思路是不对的，总需要一个变量存起来，逻辑就能变化
        //之前的逻辑是无论遍历哪一个子树，只要没有找到总会返回null，并且在当前状态就结束递归了，说明我对递归的思路还没有把握透彻
        //但是就是慢慢来，这个东西记住了，必须是这样的逻辑应当是若树中没有对应的结点，就应该全部遍历一遍，显然没有给后面一个机会
        //若要给后面一个机会就是如果左子树不为空的话，那么说明找到了，如果为空的话就没找到，必须要都遍历一边
        if(node.id == id){
            return node;
        }
        Node res = null;
        if(node.left != null){
            res = preSearch(node.left,id);
        }
        if (res!= null){
            return res;
        }
        if(node.right != null){
            res =  preSearch(node.right,id);
        }
        return res;
    }
    //中序遍历
    public void infixTraverse(Node node){
        if(node.left != null){
            infixTraverse(node.left);
        }
        System.out.println(node.id);
        if(node.right != null){
            infixTraverse(node.right);
        }
    }
    public Node infixSearch(Node node,int id){
        Node res = null;
        if(node.left != null){
            res = infixSearch(node.left,id);
        }
        if( res !=null){
            return res;
        }
        if(node.id == id){
            return node;
        }
        if(node.right != null){
            res = infixSearch(node.right,id);
        }
        return res;
    }
    //后序遍历
    //同时也可以在一个树类里面进行遍历也就是this.left.preorder
    //1.韩老师那个思路最好是在一个树里面来遍历就是在一个树类里面来遍历
    //然后每个对象既是一个结点又是一棵树也就是Tree left, Tree right这样也可以，那这样的话写在树里面就可以自己调用自己了
    //2.第二个就是还是按照之前的数据结构的思路，树类里面封装着方法，然后结点分别有不同的左右
    //这样的实现思路就是只需要在树结构内部定义一个根节点就好，但是此时由于递归的特性需要再封装一个类，否则就应该去把头节点传进去
    //其实这些不重要主要是思想或者是利用一个静态方法也可以 -- 最后这个比较好一点
    //涉及到多分叉的查找应有temp的思想
    public void postTraverse(Node node){
        if(node.left != null){
            postTraverse(node.left);
        }
        if(node.right != null){
            postTraverse(node.right);
        }
        System.out.println(node.id);
    }
    public Node postSearch(Node node,int id){
        Node res = null;
        if(node.left != null){
            res =  postSearch(node.left,id);
        }
        if( res != null){
            return res;
        }
        if(node.right != null){
            res =  postSearch(node.right,id);
        }
        if( res != null){
            return res;
        }
        if(node.id == id){
            return node;
        }
        return res;
    }
    public boolean delete(Node node,int id){//和递归的思想是一样的，但是实现方式真的是不一样
        //id是唯一的
        //在树这一章中非线性结构比较需要缓存变量
        //思路就是如果找到对应的结点就删除，比如说要删除左右节点，需要先判断左右结点是否为空
        //如果不判断的话，会出现空指针异常，整体思路不变还是递归遍历删除
        //其实就是前序删除
        if(node.left != null && node.left.id == id){
            node.left = null;
            return true;
        }
        if(node.right != null && node.right.id == id){
            node.right = null;
            return true;
        }
        //如果该结点都没有的话，这时才应该往下遍历
        if(node.left != null && delete(node.left,id)){
            return true;
        }
        if(node.right !=null && delete(node.right,id)){
            return true;
        }
        return false;
    }
    public Node getRoot(){
        return root;
    }

}
class Node{
    int id;
    char ch = '0';
    Node left;
    Node right;
    int leftType;
    int rightType;
    Node pre;
    public Node( int id){
        this.id = id;//这个赋值也能错也是够可以的
    }
    public Node( int id,char ch){
        this.id = id;
        this.ch = ch;
    }
    public String toString(){
        return id+"";
    }
}
//常见的思想是交给结点自身去递归查找，应该注意的一个点就是多分叉的结构应当是有temp的变量先存
//着这个东西最后都遍历结束再null
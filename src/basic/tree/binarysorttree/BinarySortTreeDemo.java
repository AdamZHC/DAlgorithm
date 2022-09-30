package basic.tree.binarysorttree;

public class BinarySortTreeDemo {
    public static void main(String[] args){
        int[] arr= {6,4,8,7};
        preTraverse(delete(sorted(arr),6));
    }
    public static void preTraverse(Node node){
//        System.out.println(node.id);
        if(node.left != null){
            preTraverse(node.left);
        }
        System.out.println(node.id);
        if(node.right != null){
            preTraverse(node.right);
        }
    }
    public static Node delete(Node root, int id){
        //首先找到该结点
        //此时需要TargetNode和ParentNode去查找
        Node parent = root;
        Node target = root;
        while(true){
            //如果没有找到的话
            if(target == null){
                //没有找到直接return就可以了，就是不往下输出了
                return root;
            }
            //如果找到的话
            if(target.id == id){
                break;
            }
            parent = target;
            if(id > target.id){
                target = target.right;
            }else{
                target = target.left;
            }
        }
//        if(parent != null){//因为这里是target为零，所以如果没有找到不会输出这个语句，正常逻辑是
//            //target没有找到变成null，但是parent还在上面
//           // System.out.println(parent.id);
//        }
        //删除根节点总会有问题，因此需要单独来处理
        //因为两个结点的情况不涉及到parent，不需要修改删除根节点且根节点有两个子树的情况
        if(parent == target){
            if(target.left == null && target.right == null){
                return null;
            }else if(target.left != null && target.right == null ){
                root = root.left;
                return root;
            }else if(target.left == null && target.right != null ){
                root = root.right;
                return root;
            }else{
            }
        }
        //分三种情况：
        //1.叶子结点
        //2.一个子树的结点
        //3.含有两个子树的结点
        //第一种情况 -- 直接删
        //如果要删的结点左右两个都是null就是叶子结点,这样分成三种情况
        //有三个数字第1位表示：target是parent的左子结点还是右子结点(0表示左子节点，1表示右子结点)
        //第1位表示对应上面三种情况(1,2,3不同情况)
        String flag = "0";
        if (target.left == null && target.right == null) {
            if(parent.left == target){
                flag = "00";
            }else{
                flag = "01";
            }
        }else if (target.left != null && target.right == null){
            if(parent.left == target){
                flag = "100";//第二位表示T节点关于P节点的位置，第三位表示子节点关于T结点的位置
            }else{
                flag = "110";
            }
        }else if(target.left == null && target.right != null){
            if(parent.left == target){
                flag = "101";
            }else{
                flag = "111";
            }
        }else{
            flag = "2";
        }
        if(flag.equals("00") ){
            parent.left = null;
            return root;
        }
        if(flag.equals("01")){
            parent.right = null;
            return root;
        }
        if(flag.equals("100")){
            parent.left = target.left;
            return root;
        }
        if(flag.equals("101")){
            parent.left = target.right;
            return root;
        }
        if(flag.equals("110")){
            parent.right = target.left;
            return root;
        }
        if(flag.equals("111")){
            parent.right = target.right;
            return root;
        }
        if(flag.equals("2")){
            Node min = target.right;
            Node temp = min;
            if (min.left == null){//非叶子结点位为最小的结点，还在根上
                target.id = min.id;
                target.right = min.right;
            }else{//这就是按最小值是叶节点处理的
                while(min.right != null || min.left != null){
                    temp = min;
                    min = min.left;
                }
                target.id = min.id;
                if(temp.left == min){
                    temp.left = null;
                }else{
                    temp.right = null;
                }
                return root;
            }
        }
        return root;
    }
    public static Node sorted(int[] arr) {
        Node root = new Node(arr[0]);
        Node node = null;
        Node temp = null;
        for (int i = 1; i < arr.length; i++) {
            //先把这个树创建出来
            node = new Node(arr[i]);
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
    public Node find(Node root, int val) {
        if(root == null) return null;
        if(root.val == val) return root;
        if(root.val > val && root.left != null){
            return find(root.left, val);
        }
        if(root.val < val && root.right != null){
            return  find(root.right, val);
        }
        return null;
    }
}
class Node{
    int id;
    Node left;
    Node right;
    int val;
    public Node( int id){
        this.id = id;//这个赋值也能错也是够可以的
    }

    public String toString(){
        return id+"";
    }
}

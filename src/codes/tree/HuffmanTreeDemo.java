package codes.tree;

//遇到一个选择排序的问题，每一次都选择一个最大的放在前面或者后面，注意第一次是在整体选择一个最大的，而不是在n+1选择，注意对应
public class HuffmanTreeDemo {
    public static void main(String[] args){
        int[] arr = {1,2,3,4};
        traverse(Huffmaning(arr),true,"");
    }
    public static void traverse(Node node,boolean flag,String a){
        if(node.left != null){
            a+="0";
            traverse(node.left,true,a);
            a = a.substring(0,a.length()-1);
        }
        if(node.ch!='0'){
            System.out.println(node.ch+":"+a);
        }
        if(node.right != null){
            a+="1";
            traverse(node.right,true,a);
            a = a.substring(0,a.length()-1);
        }
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
    public static Node Huffmaning(int[] arr){//返回一个Node的对象,前面加的是类型
        sort(arr);//从小到大排列
        // -- 依我之见，直接创建一个对象数组

        Node[] Huffman = new Node[arr.length];
        //初始化该对象数组
        for(int i=0;i<arr.length;i++){
            Huffman[i] = new Node(arr[i],(char)(i+97));
        }
        //不能取出两个就合并，需要有思路有规律，应当是利用插入合并的思想，先不考虑时间复杂度，无论完成怎样的和合并都应
        //当时完成一次有限长度就减一，一直往后去分析
        for(int i =0;i<arr.length-1;i++){
            Node node = new Node(Huffman[i].id+Huffman[i+1].id);//创建出这个新的结点
            node.left = Huffman[i];
            node.right = Huffman[i+1];
            //这里用到插入排序的思想
            int insertVal = node.id;
            int k =0;
            for(int j = i+1;j<Huffman.length;j++){
                //找到了可以插入的地方
                //前面有一个判断，也就是应该跳出循环的特殊情况，也就是说到了最后可以直接赋值的情况，如果到下面的语句会数组越界
                if(j==Huffman.length-1){
                    k = j;//把对应的跳出时的j的索引跳出来，这样就可以赋值了，小技巧
                    break;
                }
                if(insertVal>Huffman[j+1].id){
                    Huffman[j] = Huffman[j+1];
                }else{
                    k = j;
                    break;
                }
            }
            Huffman[k] = node;
        }
        return Huffman[Huffman.length-1];
    }
    public static void sort(int[] arr){
        for(int i=0;i < arr.length-1;i++){//i表示第几轮循环，后面就是总循环次数
            int k=i;//记录开始的索引
            for(int j=i ; j<arr.length-1 ;j++){//从第n+1索引开始找到后面数组最小或者最大的数
                if(arr[k]>arr[j]){
                    k=j;
                }
            }
            //最终得到最小值的索引
            //应当是一次内循环之后会得到一个最值
            int temp = arr[i];//arr[i]表示要确定的最大最小值放的位置
            arr[i] = arr[k];
            arr[k] = temp;
        }
    }
}
//待转化数列的全部值都在霍夫曼树中，并且都在叶子结点中，非叶子结点为了方便就约定为叶子结点的和，这样也好比较
//或者可以实现Comparable接口，从而实现抽象方法toCompare实现一个比较方法，比较方便比较，直接使用Collection.sort()的排序方法
//我的思路就是实现了一个数组是比较底层的方法，韩老师的方法就是使用比较方便的泛型类方法，思想都是一样的
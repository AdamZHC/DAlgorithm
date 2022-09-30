package codes.tree.huffmancompress;

import java.util.*;

//生成编码表
public class CodingTable {
    public static Map <Character,String> getHuffmanMap(String s){
        return tableProduce(HuffmanMode(s),"");
    }
    public static Map <Character,String> map = new HashMap <Character,String>();
//    public static void main(String[] args){
//        String s = "abbcccdddd";
//        //首先统计每个字符的个数 -- HashMap
//        System.out.println(tableProduce(HuffmanMode(s),""));
//    }
    //生成哈夫曼树
    public static Node HuffmanMode(String s){
        //生成哈希表
        Map<Character,Integer> map = new HashMap<>();//get只能获取值不能修改
        for(int i = 0; i<s.length();i++){
            if(map.get(s.charAt(i)) == null){
                map.put(s.charAt(i),1);
            }else{
                map.put(s.charAt(i),map.get(s.charAt(i))+1);//相同的key值会自动覆盖更新
            }
        }
        List<Node> Huffman = new ArrayList<>();
        //一直就是添加
        for (Map.Entry<Character,Integer> entry : map.entrySet()){
            Huffman.add(new Node(entry.getValue(),entry.getKey()));
        }
        //创建哈夫曼树
        while(Huffman.size()>1){
            //可以用优先队列，不需要直接排序
            Collections.sort(Huffman);
            Node leftNode = Huffman.get(0);
            Node rightNode = Huffman.get(1);
            Node parent = new Node(leftNode.id+rightNode.id);
            parent.left = leftNode;
            parent.right = rightNode;
            Huffman.remove(leftNode);
            Huffman.remove(rightNode);
            Huffman.add(parent);
        }
        return Huffman.get(0);
    }
    public static void sort(int[] arr){
        for(int i=0;i < arr.length-1;i++){
            int k=i;
            for(int j=i ; j<arr.length-1 ;j++){
                if(arr[k]>arr[j]){
                    k=j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[k];
            arr[k] = temp;
        }
    }
    public static Map <Character,String> tableProduce(Node node,String s){
        if(node.left != null){
            s+="0";
            tableProduce(node.left,s);
            s = s.substring(0,s.length()-1);
        }
        if(node.ch!='0'){
            map.put(node.ch,s);
        }
        if(node.right != null){
            s+="1";
            tableProduce(node.right,s);
            s = s.substring(0,s.length()-1);
        }
        return map;
    }
}
class Node implements Comparable <Node>{
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

    @Override
    public int compareTo(Node o) {
        return this.id-o.id;
    }
}
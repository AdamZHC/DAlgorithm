package basic.tree.huffmancompress;

import java.util.Map;
//韩老师使用的是字节数组
//得到二进制编码之后，把每个字符对应的二进制的字符串转成字节，放在数组中存起来，这样的话
//方法就是把字节转成二进制字符串，再转成字符，也就是说韩老师的思路就是一个技术细节，中间引入了
//字节数组，也就说二进制字符串与字节有对应关系然后用字节数组来保存编码
//这样还有一个好处就是可以处理那个二进制文件，二进制文件转化为字节数组就可以
//需要具体学这方面的内容的时候，再看看细节吧，核心思想已经完成了
public class PressProgram {
    public static String compress(String s){
        String res = "";
        Map<Character,String> map = CodingTable.getHuffmanMap(s);
        for(int i = 0; i<s.length();i++){
            res += map.get(s.charAt(i));
        }
        return res;
    }
    public static String decompress(String s,Map<String,Character> map){
        String match = "";
        String res = "";
        for(int i = 0; i<s.length(); i++){
            match += s.charAt(i);
            if(map.get(match) != null){
                res+=match;
                match = "";
            }
        }
        return res;
    }
}

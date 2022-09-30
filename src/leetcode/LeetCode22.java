package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode22 {
    public static void main(String[] args){
        int n = 0;
        List<String> dp[] = new ArrayList [n + 1];
        String s = "123";
        //子串问题就是含左不含右
        System.out.println(reString(s,3));
        //返回截断之后分开的字符串 -->
        List<String> list = new ArrayList<String>();
        list.add("213");
        //这里直接调用equals方法
        if(! list.contains("213")) list.add("213");
        System.out.println(list);
        
    }

    /**
     *
     * @param s:待重组的字符串
     * @param idx:重组的索引
     *           注：索引范围是(0~字符串的长度)
     * @return
     */
    static String reString(String s, int idx){
        if(idx == 0) return "()"+s;
        else return s.substring(0,idx) + "()" + s.substring(idx,s.length());
    }
}

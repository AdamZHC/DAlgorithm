package codes.leetcode;

import java.util.*;

public class LeetCode10 {
    public static void main(String[] args){
        new Solution10().isMatch("aaaa","c*b*.a*");
        String i = "";
        i.indexOf('a',1);
        //小根堆
        PriorityQueue<Character> p = new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return o1 - o2;
            }
        });
        p.add('a');
        p.add('c');
        System.out.println(p.remove());
    }
}
class Enterprise{
    String name;
    String id;
    public Enterprise(String name, String id){
        this.id = id;
        this.name = name;
    }
}
class Solution10 {
    public boolean isMatch(String s, String p) {
        if(s.length() == 0 && p.length() == 0) return true;
        if(s.length() == 0 || p.length() == 0) return false;
        //动态规划
        //二维dp表示状态，dp[i][j]表示是待匹配串s的前i个字符是否匹配模式串p前j个字符
        int m = s.length();
        int n = p.length();
        boolean dp[][] = new boolean[m +1][n + 1];
        dp[0][0] = true;
        dp[0][1] = false;
        //i和m是表示待匹配串的，j和n是表示模式串的
        //对于s第0字符的匹配情况
        for(int i = 2; i <= n; i ++){
            if(p.charAt(i - 1) == '*'){
                dp[0][i] = dp[0][i - 1] || dp[0][i - 2];
            }
        }
        //对于s待匹配串的前第i个（从第一个开始）的匹配情况
        for(int i = 1; i <= m; ++i) {
            //j表示模式串
            for(int j = 1; j <= n; ++j) {
                if(p.charAt(j - 1) == '*'){

//          也就是说只分两种情况：
//           1.匹配0次 ---- 相当于扔掉dp[i][j] = dp[i][j - 2]
//           2.匹配多次:
//                    dp[i][j] = dp[i − 1][j − 2],
//                    dp[i][j] = dp[i − 2][j − 2],
//                    dp[i][j] = dp[i − 3][j − 2],


                    //这里是看模式串的前一个
                    if(match(s.charAt(i - 1), p.charAt(j - 2))){
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
                        //往前找到第一个不满足的字符
                    }else{
                        dp[i][j] =  dp[i][j - 2];
                    }
                }else{
                    if(match(s.charAt(i - 1),p.charAt(j - 1))){
                        //这一个对字符匹配之后，就去看他们各自之前是否匹配
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                    //如果不能匹配的话，那就一定是不匹配的，也就是false
                }
            }
        }
        return dp[m][n];
    }
    public boolean match(char a, char b){
        if(a == '.' || b == '.') return true;
        else return a == b;
    }
}
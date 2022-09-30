package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode1525 {
    public static void main(String[] args){
        Solution5 s = new Solution5();
        System.out.println(s.numSplits("aacaba"));
    }
}
class Solution5 {
    //转成字符数组有直接的工具类，可以直接转
    public int numSplits(String s) {
        Map<Character,Integer> leftMap = new HashMap<Character,Integer>();
        Map <Character,Integer> rightMap = new HashMap <Character,Integer>();
        for(int i = 1 ; i < s.length() ; i ++){
            char ch = s.charAt(i);
            if(rightMap.get(ch) == null) rightMap.put(ch , 1);
            else rightMap.put(ch,rightMap.get(ch) + 1);
        }
        int cnt = 0;
        for(int left = 0,right = 1; right < s.length() ; left ++ , right ++){
            char leftCh = s.charAt(left);
            char rightCh = s.charAt(right);
            //把这两个哈希表再重新初始化一下
            if(leftMap.get(leftCh) == null) leftMap.put(leftCh , 1);
            else leftMap.put(leftCh , leftMap.get(leftCh) + 1);
            if(rightMap.get(rightCh) == 1) rightMap.remove(rightCh);
            else rightMap.put( rightCh , rightMap.get(rightCh) - 1);
            if(leftMap.size() == rightMap.size()) cnt++;
        }
        return cnt;
    }
}

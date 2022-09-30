package codes.leetcode;
import java.util.*;
public class LeetCode187 {
    public static void main(String[] args) {
        new Solution187().findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
    }
}
class Solution187 {
    public List<String> findRepeatedDnaSequences(String s) {
        if(s.length() <= 10){
            return new ArrayList<String>();
        }
        Map<String, Integer> map = new HashMap<>();
        List<String> ans = new ArrayList<>();
        for(int i = 0; i <= s.length() - 10; i ++){
            String e = s.substring(i, i + 10);
            if(map.get(e) == null){
                map.put(e, 1);
            }else if(map.get(e) == 1 ){
                map.put(e, 0);
                ans.add(e);
            }
        }
        return ans;
    }
}
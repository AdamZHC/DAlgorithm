package codes.leetcode;

public class LeetCode583 {
    public static void main(String[] args) {
        new Solution583().minDistance("sea","ate");
    }
}
class Solution583 {
    public int minDistance(String word1, String word2) {
        //就是贪心的思想，一定是最长可以匹配的字符串
        //以word1为主串
        int p1 = 0, p2 = 0;
        int ans = 0;
        while(true){
            if(p1 == word1.length() || p2 == word2.length()){
                break;
            }
            char ch = word1.charAt(p1);
            if(word2.indexOf(ch, p2) == -1){
                ans ++;
            }else{
                int step = word2.indexOf(ch, p2 + 1);
                p2 += step;
                ans += step;
            }
            p1 ++;
        }
        return ans + word1.length() - p1 + word2.length() - p2;
    }
}
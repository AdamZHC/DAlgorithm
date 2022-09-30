package leetcode;

public class LeetCode3 {
    public static void main(String[] args) {
        new Solution3_().lengthOfLongestSubstring("abcabcbb");
    }
}
class Solution3_ {
    public int lengthOfLongestSubstring(String s) {
        if(s.length() <= 1) return s.length();
        //好像很简单，就是一个滑窗，而且不需要维护很多东西
        //滑窗的过程需要确定对应先判断还是后判断，也即移动之后判断还是判断之后移动
        //这里采用先移动再判断，所以right就是初始化为1了
        int ans = Integer.MIN_VALUE;
        for(int left = 0, right = 1; right < s.length();){
            String subString = s.substring(left, right);
            char chLeft = s.charAt(left);
            char chRight = s.charAt(right);
            if(subString.indexOf(chRight) == -1){
                ans = right - left + 1 > ans ? right - left + 1 : ans;
                right++;
            }else{
                left = subString.indexOf(chRight) + 1;
            }
        }
        return ans;
    }
}
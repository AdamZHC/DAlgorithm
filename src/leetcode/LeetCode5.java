package leetcode;

public class LeetCode5 {
    public static void main(String[] args) {
        new Solution5_().longestPalindrome("cbbd");
    }
}
class Solution5_ {
    public String longestPalindrome(String s) {
        //中心扩展思路应当就是先进行一次Type1的判断，然后再进行Type2的判断
        //这次先是使用动态规划的思路
        //dp[i][j]表示dp[i][j]是否为回文串
        //可以在一个上面字符串上面模拟两个东西
        boolean[][] dp = new boolean[s.length()][s.length()];
        int[] ans = new int[3];
        for(int i = 0; i < s.length(); ++i) {
            for(int j = 0; j <= i; ++j) {
                if(i == j) dp[j][i] = true;
                else if(i == j + 1) dp[i][j] = s.charAt(i) == s.charAt(j);
                else dp[j][i] = dp[j + 1][i - 1] && s.charAt(i) == s.charAt(j);
                if(i - j + 1 > ans[0] && dp[j][i]){
                    ans[0] = i - j + 1;
                    ans[1] = i;
                    ans[2] = j;
                }
            }
        }
        return s.substring(ans[2], ans[1] + 1);
    }
}
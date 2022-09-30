package leetcode;

public class LeetCode1143 {
    public static void main(String[] args) {
        new Solution1143().longestCommonSubsequence("adcde","ace");
    }
}
class Solution1143 {
    public int longestCommonSubsequence(String text1, String text2) {
        //由于我们不关心前面串的字符是否匹配和前后没有关系，所以我们不需要每次往回找
        //也就是说状态转移方程的实现是时间复杂度为O(1)的
        //dp[i][j]表示在text1[0:i]和text2[0:j]前最大的公共子序长度
        int dp[][] = new int[text1.length()][text2.length()];
        //先对初始情况进行处理
        dp[0][0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;
        for(int i = 1; i < text1.length(); ++i) {
            dp[i][0] = text1.charAt(i) == text2.charAt(0) ? 1 : dp[i - 1][0];
        }
        for(int i = 1; i < text2.length(); ++i) {
            dp[0][i] = text1.charAt(0) == text2.charAt(i) ? 1 : dp[0][i - 1];
        }
        for(int i = 1; i < text1.length(); ++i) {
            for(int j = 1; j < text2.length(); ++j) {
                dp[i][j] = dp[i - 1][j - 1] + (text1.charAt(i) == text2.charAt(j) ? 1 : 0);
            }
        }
        return dp[text1.length() - 1][text2.length() - 1];
    }
}
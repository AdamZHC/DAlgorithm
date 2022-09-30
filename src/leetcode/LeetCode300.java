package leetcode;

public class LeetCode300 {
    public static void main(String[] args){
//        int[] nums = {4,10,4,3,8,9};
//        new Solution300().lengthOfLIS(nums);

        String s = "123123";
        String k = s.substring(1, 3);
        System.out.println(k);
    }
}
class Solution300 {
    public int lengthOfLIS(int[] nums) {
        //经典dp -- dp[i]表示长度为i的该数组的最长子序列的长度
        int[][] dp = new int[nums.length][2];
        //初始化
        dp[0][0] = 1; dp[0][1] = nums[0];
        //开始动规

        for(int k = 0; k < 9; k ++)
            if(k < 8) break;

        for(int i = 1; i < nums.length; i ++){
            int maxAns = 0;
            int maxNum = 0;
            for(int j = 0; j < i; j ++){
                if(nums[i] > dp[j][1] && nums[j] == dp[j][1]){
                    maxAns = Math.max(maxAns, dp[j][0] + 1);
                    maxNum = maxAns == dp[j][0] + 1 ? nums[i] : maxNum;
                }
            }
            dp[i][0] = Math.max(maxAns, dp[i - 1][0]);
            dp[i][1] = dp[i][0] == maxAns ? maxNum : dp[i - 1][1];
        }
        return dp[nums.length - 1][0];
    }
}
package codes.leetcode;

public class LeetCode213 {
    public static void main(String[] args) {
        int[] a = {2,3,2};
        new Solution213().rob(a);
    }
}
class Solution213 {
    public int rob(int[] nums) {
        //拆成两个链，假设我先从idx = 1的位置开始偷盗
        //dp[i]应当是表示到第i个房间的时候能偷窃到的最大的现金,
        //还应记录的就是取得最大值的时候是否偷盗该房间，应当有的前提是肯定房间越多，能能偷到的越多
        //即dp[i][0] >= dp[i - 1][0] >= dp[i - 2][0] >= ... >= dp[1][0] >= dp[0][0]表示最大值
        //dp[i][1]表示是否被选择
        // -- 因此状态转移方程思路就是
        //每一个点都有选择偷盗或者不偷盗两种情况，因此选择一种就可以了
        //1.dp[0] ~ dp[n - 1]
        //初始化
        int n = nums.length;
        int[][] dp0 = new int[n - 1][2];
        dp0[0][0] = nums[0];
        dp0[0][1] = 1;
        for(int i = 1; i < n - 1; ++i) {
            //假设不偷盗该房间
            int num0 = dp0[i - 1][0];
            //假设偷盗该房间
            int num1 = dp0[i - 1][1] == 0 || i == 1? dp0[i - 1][0] + nums[i] : dp0[i - 2][0] + nums[i];
            dp0[i][0] = Math.max(num0, num1);
            dp0[i][1] = num0 == dp0[i][0] ? 0 : 1;
        }
        //1.dp[1] ~ dp[n]
        int[][] dp1 = new int[n - 1][2];
        dp1[0][0] = nums[1];
        dp1[0][1] = 1;
        for(int i = 1; i < n - 1; ++i) {
            //假设不偷盗该房间
            int num0 = dp1[i - 1][0];
            //假设偷盗该房间
            int num1 = dp1[i - 1][1] == 0||i == 1 ? dp1[i - 1][0]+ nums[i + 1] : dp1[i - 2][0] + nums[i + 1];
            dp1[i][0] = Math.max(num0, num1);
            dp1[i][1] = num0 == dp1[i][0] ? 0 : 1;
        }
        return Math.max(dp0[n - 2][0], dp1[n - 2][0]);
    }
}
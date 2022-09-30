package codes.leetcode;

public class LeetCode152 {
    public static void main(String[] args) {
        int a[] = {2, -5, -2, -4, 3};
        new Solution152().maxProduct(a);
    }
}
class Solution152 {
    public int maxProduct(int[] nums) {
        if(nums.length == 1) return nums[0];
        //根据无后效性dp[i]表示以nums[i]后结尾时最大乘积
        int n = nums.length;
        int[] dp = new int[n + 1];
        int ans = 0;
        dp[0] = nums[0];
        //需要一个指针记录当前结点上一个最近的值小于零的索引
        int p = -1;
        for (int i = 1; i < nums.length; ++i) {
            //需要对于是否为负数或者0进行讨论，直接对于零进行特殊处理和干预
            if(nums[i] == 0 && i < nums.length - 1){
                while(nums[++i] == 0){
                    dp[i] = 0;
                }
                dp[i] = nums[i];
                p = nums[i] < 0 ? i : -1;
            }
            if(nums[i] < 0){
                //说明此时来到了第一个为负的阶段
                //这里有可能会有0的干预
                if(p < 0) dp[i] = Math.max(dp[i - 1] * nums[i], nums[i]);
                else{
                    if(i > p + 1) {
                        dp[i] = nums[p] * nums[i] * dp[i - 1] * (dp[p - 1] == 0 ? 1 : dp[p - 1]);
                    }else{
                        dp[i] = nums[p] * nums[i] * (dp[p - 1] == 0 ? 1 : dp[p - 1]);
                    }
                }
                p = i;
            }
            if(nums[i] > 0){
                dp[i] = dp[i - 1] * nums[i];
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
# @`LeetCode`53. 最大子数组和

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int ans = 0x80000000, n = nums.length;
        int[] dp = new int[n];
        for(int i = 0; i < n; ++i) {
            if(i == 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = nums[i] + Math.max(0, dp[i - 1]);
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
```


# @`LeetCode`300. 最长递增子序列

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int ans = 0;
        for(int i = 0; i < n; ++i) {
            int max = 0;
            for(int j = 0; j < i; ++j) 
                if(nums[j] < nums[i])
                    max = Math.max(max, dp[j]);
            dp[i] = max + 1;
            ans = Math.max(ans, dp[i]);
        }
        // System.out.println(Arrays.toString(dp));
        return ans;
    }
}
```


# @`LeetCode`279. 完全平方数

```java
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 0x7fffffff);
        dp[0] = 0;
        for(int i = 1; i <= n; ++i) {
            int m = 1;
            while(i - m * m >= 0) {
                dp[i] = Math.min(dp[i], dp[i - m * m] + 1);
                m++;
            }
                
        }
        return dp[n];
    }
}
```


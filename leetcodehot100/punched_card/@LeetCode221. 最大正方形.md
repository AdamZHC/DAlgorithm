# @`LeetCode`221. 最大正方形

```java
class Solution {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; ++i)
            for(int j = 0; j < n; ++j)
                dp[i][j] = matrix[i][j] == '1' ? 1 : 0;
        int ans = 0;
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(dp[i][j] == 0)
                    continue;
                int v = 0;
                if(chk(i - 1, j, m, n) && chk(i, j - 1, m, n) && chk(i - 1, j - 1, m, n))
                    v = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                dp[i][j] += v;
                ans = Math.max(ans, dp[i][j]);
            }
        }
        // for(int[] d : dp)
        //     System.out.println(Arrays.toString(d));
        return ans * ans;
    }
    boolean chk(int i, int j, int m, int n) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }
}
```


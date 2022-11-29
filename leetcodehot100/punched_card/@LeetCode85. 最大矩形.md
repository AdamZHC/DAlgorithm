# @`LeetCode`85. 最大矩形

```java
class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(i == 0) {
                    dp[i][j] = matrix[i][j] == '1' ? 1 : 0;
                } else {
                    if(matrix[i][j] == '1')
                        dp[i][j] = dp[i - 1][j] + 1;
                }
            }
        }
        // for(int[] d : dp)
        //     System.out.println(Arrays.toString(d));
        int ans = 0;
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                int min = 0x7fffffff;
                for(int k = j; k >= 0; --k) {
                    if(matrix[i][k] == '0')
                        break;
                    min = Math.min(min, dp[i][k]);
                    ans = Math.max(ans, (j - k + 1) * min);
                }
            }
        }
        return ans;
    }
}
```


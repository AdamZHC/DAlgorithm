# @`LeetCode`647. 回文子串

```java
class Solution {
    public int countSubstrings(String s) {
        char[] chs = s.toCharArray();
        int n = chs.length, ans = 0;
        boolean[][] dp = new boolean[n][n];
        for(int len = 1; len <= n; ++len) {
            for(int i = 0; i < n; ++i) {
                int j = i + len - 1;
                if(j >= n)
                    break;
                if(i == j) {
                    dp[i][j] = true;
                    ans++;
                } else {
                    if(i + 1 == j) {
                        dp[i][j] = chs[i] == chs[j];
                    } else {
                        dp[i][j] = chs[i] == chs[j] && dp[i + 1][j - 1];
                    }
                    if(dp[i][j])
                        ans++;
                }    
            }
        }
        return ans;
    }
}
```


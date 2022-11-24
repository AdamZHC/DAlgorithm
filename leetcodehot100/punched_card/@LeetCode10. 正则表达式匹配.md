# @`LeetCode`10. 正则表达式匹配

```java
class Solution {
    public boolean isMatch(String s, String p) {
        s = "#" + s;
        p = "#" + p;
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m][n];
        dp[0][0] = true;
        for(int i = 1; i < n; ++i)
            if(p.charAt(i) == '*')
                dp[0][i] = dp[0][i - 2];
        for(int i = 1; i < m; ++i) {
            for(int j = 1; j < n; ++j) {
                char a = s.charAt(i);
                char b = p.charAt(j);
                if(match(a, b)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    if(b == '*')
                        dp[i][j] = dp[i - 1][j] && match(a, p.charAt(j - 1)) || dp[i][j - 2];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
    boolean match(char a, char b) {
        return a == b || a == '.' || b == '.';
    }
}
```


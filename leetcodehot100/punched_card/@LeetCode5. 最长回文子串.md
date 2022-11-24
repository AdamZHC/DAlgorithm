# @`LeetCode`5. 最长回文子串

```java
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String ans = "";
        int lenn = 0;
        for(int len = 1; len <= n; ++len) {
            for(int l = 0; l < n; ++l) {
                int r = l + len - 1;
                if(r >= n)
                    break;
                if(l == r)
                    dp[l][r] = true;
                else if(r - l == 1)
                    dp[l][r] = s.charAt(l) == s.charAt(r);
                else
                    dp[l][r] = dp[l + 1][r - 1] && s.charAt(l) == s.charAt(r);
                if(dp[l][r]) 
                    if(len > lenn) {
                        ans = s.substring(l, r + 1);
                        lenn = len;
                    }
            }
        }
        return ans;
    }
}
```


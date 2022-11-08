# @`LeetCode`3.无重复字符的最长子串

### 思路

滑动窗口入门题，用`map`维护窗口

### 代码

```java
class Solution {
    char meta = (char)0;
    public int lengthOfLongestSubstring(String s) {
        int[] hash = new int[200];
        int ans = 0, n = s.length();
        char[] chs = s.toCharArray();
        for(int l = 0, r = 0; r < n; ++l) {
            while(r < n && hash[chs[r] - meta] == 0)
                hash[chs[r++] - meta]++;
            ans = Math.max(ans, r - l);
            hash[chs[l] - meta]--;
        }
        return ans;
    }
}
```


# @`LeetCode`96. 不同的二叉搜索树

### 思路

我们这样考虑，考虑拆分**子问题**，对于某棵树来说，我们尝试**枚举根节点**，我们可以得到`n + 1` 结点的树，有`n`种情况：`(0, n), (1, n - 1), (2, n - 2)...(n - 1, 1), (n, 0) `

那么`n + 1`的不同的树形就是这`n`种情况的总和，每种情况即为**左右子树**的**个数之积**

即为`dp`的思想

### 代码

```java
class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1; dp[1] = 1;
        for(int i = 2; i <= n; ++i) 
            for(int j = 0; j < i; ++j) 
                dp[i] += dp[j] * dp[i - j - 1];
        return dp[n];
    }
}
```


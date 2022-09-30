# @动态规划——(专题)常见的定义方法以及题型

1. 涉及到字符串匹配问题需要看是否匹配——正则表达式匹配

```java
//一般情况下涉及到的维度比较
//例如会有二维的dp还有就是——交错字符串
//最长公共子序列
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
```

2.背包问题

**枚举金额**

```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        //动态规划
        //dp[i]表示组成i金额的最少硬币个数
        //状态转移方程：dp[i] = Math.min(dp[i - coins[0]] + 1,...,dp[i - coins[k]] + 1)
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for(int i = 1; i <= amount; ++i) {
            int min = Integer.MAX_VALUE;
            boolean flag = true;
            for(int j = 0; j < coins.length; ++j) {
                if(i - coins[j] >= 0 && dp[i - coins[j]] != -1){
                    flag = false;
                    min = Math.min(min, dp[i - coins[j]] + 1);
                }
            }
            dp[i] = flag ? -1 : min; 
        }
        return dp[amount];
    }
}
```

3. 最长上升子序列——子问题的特殊定义

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }
}
```

4. 树形dp——定义状态(还需要dfs)

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
 //注意中序遍历可以把正确的顺序遍历出来的指的是bts排序二叉树
 //要把树转成数组还得是广度优先搜索
class Solution {
    public int rob(TreeNode root) {
        //每个结点经过遍历后存每个以该结点为父节点的最大金额
        //深度优先搜索+动态规划 -- 有递归的思想
        //首先是遍历到最底层 -- 不需要改变数组直接存在对应的结点上
        //该递归函数表示目的结点最大的值 -- 由动态规划的思想
        //推测只有两种可能就是都在n-1层或者都在n-2层
        //不需要处理，因为对于一个结点来说都是这样的
        if(root.left != null){
            //rob的意思就是达到最大，从某一个函数体来讲，就是让他为根结点的下面的树达到最大（进行动规）
            //从宏观来讲，就是让其深度优先搜索，一直达到最底层，进行动规
            rob(root.left);
        }
        if(root.right != null){
            rob(root.right);
        }
        int f = Flayer(root);
        int s = Slayer(root);
        root.val = Math.max(f,s+root.val);
        return  root.val;
    }
    public int Flayer(TreeNode node){
        int leftNum = node.left == null? 0:node.left.val;
        int rightNum = node.right == null? 0:node.right.val;
        return leftNum+rightNum;
    }
    public int Slayer(TreeNode node){
        TreeNode leftNode = node.left;
        int maxLeft = leftNode == null ? 0:Flayer(leftNode);
        TreeNode rightNode = node.right; 
        int maxRight = rightNode == null ? 0:Flayer(rightNode);
        return maxLeft + maxRight;
    }
}
```

5. 动态规划预处理(判断回文串)

```java
class Solution {
    public List<List<String>> partition(String s) {
        //我感觉这个题应该就是爆搜
        //我实在是想不出什么动态规划的思路(有可能是状压dp)
        //按照处理边界的思路————这时候可以把单个的回文串放到一个里面或者用区间的思想
        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], true);
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }

        dfs(s, 0);
        return ret;
    }
        public void dfs(String s, int i) {
        if (i == n) {
            ret.add(new ArrayList<String>(ans));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (f[i][j]) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }
}
```

6. 路径dp(机器人，杨辉三角)

```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; ++i) {
            dp[i][0] = 1;
        }
        for(int j = 0; j < n; ++j) {
            dp[0][j] = 1;
        }
        for(int i = 1; i < m; ++i) {
            for(int j = 1; j < n; ++j) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
```

7. 多路归并(丑数)

```java
class Solution {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(num2, num3), num5);
            if (dp[i] == num2) {
                p2++;
            }
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }
        }
        return dp[n];
    }
}
```
8. 多状态dp——在实际问题中因为有买卖股票，所以状态非常多就使用多状态
```java
class Solution {
    //含冷冻期
    //也是要实现动态规划
    //从最优子结构考虑——可以有很多的定义方法
    //从无后效性来考虑，一定要把每个阶段的信息定义完全
    //这里的含义就是说，我们最后需要的那个状态的需要其它状态的协助来实现的
    //以上的思想给了动态规划思想的一个新的思路，那就是定义一个阶段的多个状态信息从而
    //实现最后的我们需要的那个状态
    //那对应的就有三个状态 1.持股 2.不持股(非冷冻) 3.不持股(冷冻)
    //其实不怕浪费时间，这里典型的就是有后效性!!!如果说选择买票或者不买票
    public int maxProfit(int[] prices) {
        //再使用滚动数组——a,b,c
        int n = prices.length;
        //通过定于多个状态来实现
        //因为都枚举过了，所以我们不关心之前状态的实现
        //dp[i][0]表示持股实现的最大利润
        //dp[i][1]刚把股票卖了的最大利润
        //dp[i][2]已经卖了一阵子了的最大利润
        //实现最大的不持股的情况，是从哪里来的
        // int[][] dp = new int[n][3];
        //边界条件的实现
        // dp[0][0] = -prices[0];
        int a = -prices[0], b = 0, c = 0; 
        for(int i = 1; i < n; ++i) {
           //此时对于一般情况来说的动态规划
           int NA = Math.max(a, c - prices[i]);
           int NB = a + prices[i];
           int NC = Math.max(b, c);
           a = NA; b = NB; c = NC;
        
        }
        return Max(a, b, c);
        
    }

    int Max(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }
}
```
8. 正向刷表dp
```java
class Solution {
    public long mostPoints(int[][] questions) {
        var n = questions.length;
        var f = new long[n + 1];
        for (var i = 0; i < n; i++) {
            f[i + 1] = Math.max(f[i + 1], f[i]);
            var q = questions[i];
            var j = Math.min(i + q[1] + 1, n);
            f[j] = Math.max(f[j], f[i] + q[0]);
        }
        return f[n];
    }
}
```
9. 反向序列dp——而只有两种的情况下，并且题目给出的情况下，可以考虑使用一般的dp，也就是直接的不用枚举状态的
其实说白了有这两个的分别没那么清楚，像上一个的部分也可以用反向dp来求解，所以说其实没有那么学院化和固定化，就看头行不行
```java
class Solution {
    public long mostPoints(int[][] questions) {
        var n = questions.length;
        var f = new long[n + 1];
        for (var i = n - 1; i >= 0; --i) {
            var q = questions[i];
            var j = i + q[1] + 1;
            f[i] = Math.max(f[i + 1], q[0] + (j < n ? f[j] : 0));
        }
        return f[0];
    }
}
```
10. 对于子序列和子数组(子串)的dp其实都可以使用dp来处理！
最长递增子序列和最长重复数组的dp[i][j]其实都是以i,j为结尾的最长子串
不过是前者是O(n2)后者是O(n)的，前面的是从前面的所有的状态转移过来，但是后这直接可以转移过来
其实都可以使用dp的思想，题设在于连续不连续，其实反而连续更好解决——注意滑窗也可以处理
```java
class Solution {
    public int findLength(int[] A, int[] B) {
        int n = A.length, m = B.length;
        int[][] dp = new int[n + 1][m + 1];
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                dp[i][j] = A[i] == B[j] ? dp[i + 1][j + 1] + 1 : 0;
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
}
```
11. 特殊的序列dp没有用对应的索引来作为参数来定义而是把题目中的状态来作为定义
比较特别
```java
class Solution {
    public double largestSumOfAverages(int[] A, int K) {
        int N = A.length;
        double[] P = new double[N+1];
        for (int i = 0; i < N; ++i)
            P[i+1] = P[i] + A[i];

        double[] dp = new double[N];
        for (int i = 0; i < N; ++i)
            dp[i] = (P[N] - P[i]) / (N - i);

        for (int k = 0; k < K-1; ++k)
            for (int i = 0; i < N; ++i)
                for (int j = i+1; j < N; ++j)
                    dp[i] = Math.max(dp[i], (P[j]-P[i]) / (j-i) + dp[j]);

        return dp[0];
    }
}
```

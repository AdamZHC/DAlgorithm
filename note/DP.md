# @动态规划(专题——定义技巧)

### 从某种角度上来说：动态规划是一种有策略的枚举

### 因此尽量要对于定义是要完全的定义状态

#### 例如：

#### 树形dp——上司的舞会

状态定义就是**1选择这个结点**的最大快乐值，**0不选择这个结点**的最大快乐值，从这个角度来讲这样就实现了对应的完全的枚举

```c++
#include<bits/stdc++.h>
using namespace std;
#define MAXN 6005
int h[MAXN];
int v[MAXN];
vector<int> son[MAXN];
int f[MAXN][2];
void dp(int x)
{
 f[x][0]=0;
 f[x][1]=h[x];
 for(int i=0;i<son[x].size();i++)
 {
  int y=son[x][i];
  dp(y);
  f[x][0]+=max(f[y][0],f[y][1]);
  f[x][1]+=f[y][0];
 }
}
int main()
{
 int n;
 cin>>n;
 for(int i=1;i<=n;i++) cin>>h[i];
 for(int i=1;i<=n-1;i++)
 {
  int x,y;
  cin>>x>>y;
  son[y].push_back(x);
  v[x]=1;
 }
 int root;
 for(int i=1;i<=n;i++)
 if(!v[i]) {root=i;break;}
 dp(root);
 cout<<max(f[root][0],f[root][1])<<endl;
 return 0;
}
```

#### 常规dp——打家劫舍

这里状态虽然没有定义到二维，但是其实现了所有问题的考虑，不需要二维，只需要一维即可以实现无后效性

```java
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[length - 1];
    }
}
```



### 无后效性的实现

与上面相同那就是这一状态的实现一定取决于前若干状，而后一状态的决定不会影响到前一状态的实现简单点说就是dp[i]定了之后，dp[i + 1]无论怎么变都不会影响到前面的dp[i]——这样实现的思路需要对于状态信息的全面定义(加一维或者完善状态转移方程)无后效性的实现也
可以通过对应的状态转移方程定义实现不一定非要，也就是之前记录的两边有干扰的情况下可以认定为只有一边，其实简单一点就是说可通过不同的方式实现对应到的
无后效性，当然加一维也可以实现实际上不能扣这种牛角尖，关键还是在如何学习处理的技巧而不是讨论理论(主要是我也讨论不清楚)

### 例如：

最大乘积连续序列

```java
class Solution {
    public int maxProduct(int[] nums) {
        int length = nums.length;
        int[] maxF = new int[length];
        int[] minF = new int[length];
        System.arraycopy(nums, 0, maxF, 0, length);
        System.arraycopy(nums, 0, minF, 0, length);
        for (int i = 1; i < length; ++i) {
            maxF[i] = Math.max(maxF[i - 1] * nums[i], Math.max(nums[i], minF[i - 1] * nums[i]));
            minF[i] = Math.min(minF[i - 1] * nums[i], Math.min(nums[i], maxF[i - 1] * nums[i]));
        }
        int ans = maxF[0];
        for (int i = 1; i < length; ++i) {
            ans = Math.max(ans, maxF[i]);
        }
        return ans;
    }
}
```

### 状态转移方程的实现

这就需要很复杂的想法可以怎样实现,涉及到最优子结构，最优子结构以及是否能递推，是否有这样的关系
其实这里也会涉及到证明等的问题，是否能够推出下个规模更大的问题，这里需要很复杂的考量，有无后效性是
可以实现显而易见，但是状态转移方程的实现需要考量，当然一定程度上不能太死抠，否则也会出问题

###自己的思考——来源于于若干的题目
1. 对于线性的增长的dp我们总可以知道，dp的最优解一定是越来越大的这是来源于贪心思想，也就是说最优解一定是比前面的最优解要更加优的
这样才能体现出规划问题的思想
2. 相对于是否选择该点的dp定义则是与上面不同的，这时的dp不一定线性增长，因此需要实时维护最优值——最优解，**当然上面这两种和对应的时间复杂度没有关系，并不是是说有O(n)和O(n2)的区别
需要把能转移过来的状态都要访问一边才行**
   1. 对于以上两种情况，如果完全枚举了各个状态的情况，那就属于第一种的线性递增dp，这样的dp其实就是枚举状态的情况，等价于dp[0:i]中最大的最优解，因为通过枚举状态已经把所有的状态已经
   完成了，但是对于最长上升子序列的情况就是需要维护答案的，其实你看最长上升子序列的是否选择状态是暗含在完全枚举状态的情况下的，也就是说此时的，但是有时候不能完全确定到底是否这样的只能是整体的情况下的
   这就是动态规划太活了，所以不能有一个定论而是只能来有一个经验论——有时候**直接转移**这意味着没有最优的比较原则，但是有时候会体现贪心思想，有比较的最优原则
   2. 还有一种情况那就是返向dp和正向刷表dp也就是在i的是否处理到i + 1和 i + a[i] + 1的情况
3. 对于序列的选择和序列dp会用到dp[i][j]涉及到二维dp
4. 对于许多回溯算法，搜索算法，起始可以根据dp的思想去优化对应的算法，这也就是所谓的记忆化搜索
5. 是否连续序列这里有时间复杂度和前面状态的转移过程分别有最长递增序列和最长重复数组
###序列dp新的处理思路
1. 把数组分成K个相邻的非空子数组，这样的思想就是可以把K作为一个dp数组的一个参数
然后实现对应的规划问题，注意是把K作为参数，而不是把序列dp的二维索引定义
2. 其实很好实现，可以从前面的状态转移过来
3. 注意到「将数组分割为K段，求……」是动态规划题目常见的问法而且往往K要作为一个维度来处理
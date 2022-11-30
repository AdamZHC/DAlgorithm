# @`LeetCode`238. 除自身以外数组的乘积

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] pr = new int[n];
        int[] lr = new int[n];
        for(int i = 0; i < n; ++i)
            if(i == 0)
                pr[i] = nums[i];
            else
                pr[i] = nums[i] * pr[i - 1];

        for(int i = n - 1; i >= 0; --i)
            if(i == n - 1)
                lr[i] = nums[i];
            else
                lr[i] = nums[i] * lr[i + 1];

        int[] ans = new int[n];
        for(int i = 0; i < n; ++i) {
            if(i == 0)
                ans[0] = lr[1];
            if(i == n - 1)
                ans[n - 1] = pr[n - 2];
            if(i != 0 && i != n - 1)
                ans[i] = pr[i - 1] * lr[i + 1];
        }
        return ans;
    }
}
```


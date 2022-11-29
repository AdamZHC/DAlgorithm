# @`LeetCode`338. 比特位计数

```java
class Solution {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for(int i = 0; i <= n; ++i)
            ans[i] = cnt(i);
        return ans;
    }
    int cnt(int a) {
        int res = 0;
        while(a != 0) {
            if((a & 1) == 1)
                res++;
            a = a >> 1;
        }
        return res;
    }
}
```


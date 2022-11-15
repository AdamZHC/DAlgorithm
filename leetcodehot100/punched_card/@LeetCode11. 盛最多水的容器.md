# @`LeetCode`11. 盛最多水的容器

```java
class Solution {
    public int maxArea(int[] h) {
        int n = h.length;
        int l = 0, r = n - 1;
        int ans = 0;
        while(l < r) {
            if(h[l] <= h[r]) {
                ans = Math.max(ans, h[l] * (r - l));
                l++;
            } else {
                ans = Math.max(ans, h[r] * (r - l));
                r--;
            }
        }
        return ans;
    }
}
```


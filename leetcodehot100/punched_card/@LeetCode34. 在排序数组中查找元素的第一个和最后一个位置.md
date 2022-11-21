# @`LeetCode`34. 在排序数组中查找元素的第一个和最后一个位置

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while(l < r) {
            int mid = l + r >> 1;
            if(nums[mid] >= target)
                r = mid;
            else
                l = mid + 1;
        }
        int e1 = l;
        l = 0; r = n - 1;
        while(l < r) {
            int mid = l + r + 1 >> 1;
            if(nums[mid] <= target)
                l = mid;
            else
                r = mid - 1;
        }
        int e2 = r;
        if(e2 < e1 || target != nums[e1])
            return new int[]{-1, -1};
        else
            return new int[]{e1, e2};
    }
}
```


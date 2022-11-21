# @`LeetCode`33. 搜索旋转排序数组

```java
class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;
        //旋转位置
        while(l < r) {
            int mid = l + r >> 1;
            if(chk(nums, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        //
        if(target == nums[l])
            return l;
        int k1 = bs(nums, l, n - 1, target);
        if(k1 != -1)
            return k1;
        int k2 = bs(nums, 0, l - 1, target);
        if(k2 != -1)
            return k2;
        return -1;

    }
    boolean chk(int[] nums, int mid) {
        int n = nums.length;
        return nums[mid] <= nums[0] && nums[mid] <= nums[n - 1];
    }
    int bs(int[]nums, int l, int r, int target) {
        if(l > r)
            return -1;
        while(l < r) {
            int mid = l + r >> 1;
            if(nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if(nums[l] == target)
            return l;
        else
            return -1;
    }
}
```


# @`LeetCode`287. 寻找重复数

```java
class Solution {
    public int findDuplicate(int[] nums) {
        int n = nums.length, ans = 0;
        for(int i = 0; i < n; ++i) {
            int k = Math.abs(nums[i]) - 1;
            if(nums[k] < 0) {
                ans = k + 1;
                break;
            } else
                nums[k] *= -1;

        }
        for(int i = 0; i < n; ++i)
            nums[i] = Math.abs(nums[i]);
        return ans;
    }
}
```


# @`LeetCode`55. 跳跃游戏

```java
class Solution {
    public boolean canJump(int[] nums) {
        int max = 0, n = nums.length;
        for(int i = 0; i < n; ++i) {
            if(max < i) 
                return false;
            max = Math.max(max, i + nums[i]);
        }
        return true;
    }
}
```


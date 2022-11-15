# @`LeetCode`283. 移动零

```java
class Solution {
    public void moveZeroes(int[] nums) {
        //双指针
        int p1 = 0, p2 = 0, n = nums.length;
        while(p1 < n && p2 < n) {
            //找0;
            while(p1 < n && nums[p1] != 0)
                p1++;
            while(p2 < n && p2 <= p1)
                p2++;
            while(p2 < n && nums[p2] == 0)
                p2++;
            if(p1 < n && p2 < n)
                swap(nums, p1, p2);
        }
    }
    void swap(int[] nums, int a, int b) {
        nums[a] = nums[a] ^ nums[b];
        nums[b] = nums[a] ^ nums[b];
        nums[a] = nums[a] ^ nums[b];
    }
}
```


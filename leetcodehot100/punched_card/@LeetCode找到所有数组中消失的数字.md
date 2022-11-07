# @`LeetCode`找到所有数组中消失的数字

### 思路

**原地哈希**，如果出现过的话，就把**对应索引**上的值置为**负值**，最终**遍历一下**如果某个索引上的值为正值的话，说明该**索引**没有出现过，那就作为答案扔到`List`中

### 代码

```java
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int n = nums.length;
        for(int i = 0; i < n; ++i) {
            int val = Math.abs(nums[i]);
            if(nums[val - 1] < 0)
                continue;
            nums[val - 1] *= -1;
        }
        for(int i = 0; i < n; ++i)
            if(nums[i] > 0)
                ans.add(i + 1);
        return ans;
    }
}
```


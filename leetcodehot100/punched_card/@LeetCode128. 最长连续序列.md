# @`LeetCode`128. 最长连续序列

### 思路

这个题目是做过好几次了，思路还是不能第一时间想到，这次要记下来，不是**常规题**，算那种**做过才能会的**

扔到`set`里面，循环查找就行，如果`num - 1`也在的话就跳过，这是降低时间复杂度的关键

### 代码

```java
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int ans = 0;
        for(int e : nums)
            set.add(e);
        for(int e : set) {
            if(set.contains(e - 1))
                continue;
            int cnt = 1;
            while(set.contains(e + cnt))
                cnt++;
            ans = Math.max(ans, cnt);
        }
        return ans;
    }
}
```


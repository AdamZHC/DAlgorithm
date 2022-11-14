# @`LeetCode`239. 滑动窗口最大值

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        //单调双端队列
        Deque<Integer> dq = new ArrayDeque<>();
        int n = nums.length, idx = 0;
        int[] ans = new int[n - k + 1];
        for(int i = 0; i < n; ++i) {
            while(!dq.isEmpty() && nums[dq.peekLast()] <= nums[i])
                dq.pollLast();
            dq.offerLast(i);
            if(i >= k - 1) {
                while(!dq.isEmpty() && dq.peekFirst() <= i - k)
                    dq.pollFirst(); 
                ans[idx++] = nums[dq.peekFirst()];
            }
        }
        return ans;
    }
}
```


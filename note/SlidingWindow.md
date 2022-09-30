# @滑动窗口

滑动窗口（双指针）有模板，((不需要自己去思考顺序否则就思路比较混乱)这是在放屁
每次写得痛快的时候都是用的脑子写的，所以每次写的时候需要有一定的规范即可
不需要定得太死，不然真的会死的，需要要脑子去写，而不是照着模板写——基本的约定俗成就是左闭右开就行其他的再说
)(前面的东西也是在放屁)——注意此时应当有的考量就就是l = 0, r = 0开始，然后无论如何r一定要++
之后那就可以开始，然后一般此时也还是或者直接用for循环，具体到这个细节时的分析有多种——类似于二分，但是此时我们选择
因为二分都有每个人的固定写法，所以我选择固定起来使用比较通俗一点的双while循环这样比较直观，我们总维护左闭右开
并且始终满足对应的right > left——比较规整，反正都会还，都也可以写一写

```java
//就按照下面这个写
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        //滑动窗口
        int n = nums.length, sum = 0;
        int ans = 0x7fffffff;
        //滑动窗口的模板原来这么简单
        for(int l = 0, r = 0; l < n; ++l) {
            //一直拓展直到不能再次拓展
            while(r < n && sum < target) {
                sum += nums[r++];
            }
            //满足就更新答案
            if(sum >= target)
                ans = Math.min(ans, r - l);
            //因为已经拓展到不能拓展，所以说只能更新窗口值
            sum -= nums[l];
        }
        return ans == 0x7fffffff ? 0 : ans;
    }
}
```
下面这样写也没毛病

先单个入窗
允许第一个条件超出预定范围
并且维护的窗口满足的范围是含l不含r
就是说是这样的[l , r)
在初始时刻都是固定的也就是l = 0, r = 0时表示窗口为空(约定)
特殊情况下需要初始化为l = 0, r = 1保证窗口内有值
注意要灵活，否则会死！！！保证左闭右开

单调队列的滑动窗口问题——非常经典，这时候双端队列的应用非常意思
```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < k; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }
}

```


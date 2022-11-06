# @`LeetCode`739. 每日温度

### 思路

单调栈模板题

### 代码

```java
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        //单调栈
        Stack<Integer> s = new Stack<>();
        //算是模板题
        int n = temperatures.length;
        int[] ans = new int[n];
        for(int i = 0; i < n; ++i) {
            if(s.isEmpty()) {
                s.push(i);
            } else {
                while(!s.isEmpty() && temperatures[i] > temperatures[s.peek()]) {
                    ans[s.peek()] = i - s.peek();
                    s.pop();
                }
                s.push(i);
            }
        }
        return ans;
    }
}
```


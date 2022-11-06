# @`LeetCode`84. 柱状图中最大的矩形

### 思路

本质上就是用来处理**贡献**，也就是左侧或右侧的贡献

### 代码

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        //单调栈——预处理贡献
        int ans = 0;
        int n = heights.length;
        int[] conn = new int[n];
        Stack<Integer> s = new Stack<>();
        for(int i = 0; i < n; ++i) {
            if(s.isEmpty()) {
                s.push(i);
            } else {
                while(!s.isEmpty() && heights[i] <= heights[s.peek()])
                    s.pop();
                if(s.isEmpty())
                    conn[i] = i;
                else
                    conn[i] = i - s.peek() - 1;
                s.push(i);
            }
        }
        // System.out.println(Arrays.toString(conn));
        s.clear();
        for(int i = n - 1; i >= 0; --i) {
            int cc = 0;
            if(s.isEmpty()) {
                s.push(i);
            } else {
                while(!s.isEmpty() && heights[i] <= heights[s.peek()]) 
                    s.pop();
                if(s.isEmpty())
                    cc = n - i - 1;
                else
                    cc = s.peek() - i - 1;
                s.push(i);
            }
            ans = Math.max(ans, (conn[i] + cc + 1) * heights[i]);
        }
        return ans;
    }
}
```


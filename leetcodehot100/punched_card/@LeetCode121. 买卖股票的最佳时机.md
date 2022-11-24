# @`LeetCode`121. 买卖股票的最佳时机

```java
class Solution {
    public int maxProfit(int[] prices) {
        Stack<Integer> s = new Stack<>();
        int ans = 0;
        for(int e : prices) {
            if(s.isEmpty())
                s.push(e);
            else {
                while(!s.isEmpty() && e < s.peek()) 
                    s.pop();
                s.push(e);
                ans = Math.max(ans, e - s.firstElement());
            }
        }
        return ans;
    }
}
```


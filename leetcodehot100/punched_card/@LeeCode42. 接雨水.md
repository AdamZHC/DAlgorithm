# @`LeeCode`42. 接雨水

### 思路

之前一直是按照`dp`做的，因为今天专题的原因，就用**单调栈**来做，需要熟悉单调栈用来处理`NGE`问题，另外这道题的细节在于需要保证`stack`中存在**两个元素**才有效

**维护`bottom`到`top`单调递减的栈**，每次出栈的时候计算此时的**积水**，积水的计算需要依赖于**上面的细节**，宽度很简单就是**两元素下标相减**，然后高度是**此元素和待出栈元素较小值减去上一个出栈元素**

### 代码

```java
class Solution {
    public int trap(int[] height) {
        //单调栈——遍历结束之后留下最值
        //从栈底到栈顶保持递减
        Stack<Integer> s = new Stack<>();
        int n = height.length;
        int ans = 0;
        for(int i = 0; i < n; ++i) {
            if(s.isEmpty()) {
                s.push(i);
            } else {
                while(!s.isEmpty() && height[s.peek()] <= height[i]) {
                    int t = s.pop();
                    if(s.isEmpty())
                        break;
                    int p = s.peek();
                    ans += (i - s.peek() - 1) * (Math.min(height[i], height[p]) - height[t]);
                }
                s.push(i);
            }
        }
        return ans;
    }
}
```


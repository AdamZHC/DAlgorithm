# @`LeetCode`32.最长有效括号

### 思路

这道题目还是比较难得，如果用不使用`dp`（契合主题）使用`stack`的话，需要维护的情况和一般维护的不太一样

明晰一点：**更新的值**总是每个以`s[i] == ')'`**结尾**的**最大长度**

因此我们考虑如何获得该值，如果是`s[i] == '('`**显然不需要更新**

如果是`s[i] == '('`

在栈底维护**最后一个没有被匹配的索引**，如果说`s.peek()`命中的话，意味着这个是上一个**合法串的结尾** 如果为空的话，那就**入栈** 命中`s[i] == '('`总是保证以`s[i]`**结尾**的串长度

### 代码

```java
class Solution {
    public int longestValidParentheses(String s) {
        char[] chs = s.toCharArray();
        int ans = 0, n = chs.length;
        Stack<Integer> ss = new Stack<>();
        ss.push(-1);
        for(int i = 0; i < n; ++i) {
            if(chs[i] == '(') {
                ss.push(i);
            } else {
                ss.pop();
                if(ss.isEmpty()) {
                    ss.push(i);
                } else {
                    ans = Math.max(ans, i - ss.peek());
                }
                    
            }
        }
        return ans;
    }
}
```


# @`LeetCode`20. 有效的括号

### 思路

用**栈**去模拟遍历的过程，如果**栈为空或者左括号**那就入栈，如果为**右括号**考虑是否需要出栈再入栈

出栈的条件是**左右括号相遇**

### 代码

```java
class Solution {
    public boolean isValid(String s) { 
        Stack<Character> ss = new Stack<>();
        char[] chs = s.toCharArray();
        for(char ch : chs) {
            if(ss.isEmpty() || ch == '(' || ch == '{' || ch == '[') {
                ss.push(ch);
            } else {
                if(Math.abs(ch - ss.peek()) <= 2 && ch != ss.peek())
                    ss.pop();
                else
                    ss.push(ch);
            }
            // System.out.println(ss);
        }
        return ss.isEmpty();
    }
}
```


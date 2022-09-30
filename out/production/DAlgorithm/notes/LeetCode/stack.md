# @括号问题---栈

很多情况下，括号的问题可以不用栈模拟，只需要用两个计数器及左括号和右括号维护就可以，不需要声明栈

```java
for (char c : s) {
            if (c == '(') {
                lremove++;
            } else if (c == ')') {
                if (lremove == 0) {
                    rremove++;
                } else {
                    lremove--;
                }
            }
        }
```

如图所示，另外模拟栈空的情况时即为rremove == lremove

甚至是只用一个变量维护括号，即为是stack++ ,stack--遇到不同的括号进行不同的判断和操作
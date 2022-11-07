# @`LeetCode`17. 电话号码的字母组合

### 思路

使用**`hash`**维护一下每个号码对应的字母

然后每次就是用`digits[i]`的**字符**进行添加，添加到上一位剩下的字符串数组中，循环`k`次

**每次结束**之后需要替换上一次的，也就是**更换一下指针**

### 代码

```java
class Solution {
    char[][] hash = {
        {}, {},
        {'a', 'b', 'c'},
        {'d', 'e', 'f'},
        {'g', 'h', 'i'},
        {'j', 'k', 'l'},
        {'m', 'n', 'o'},
        {'p', 'q', 'r', 's'},
        {'t', 'u', 'v'},
        {'w', 'x', 'y','z'}
    };
    public List<String> letterCombinations(String digits) {
        List<String> ll = new ArrayList<>();
        int n = digits.length();
        for(int i = 0; i < n; ++i) {
            int k = digits.charAt(i) - '0';
            List<String> cc = new ArrayList<>();
            if(ll.isEmpty()) {
                for(char ch : hash[k])
                    cc.add("" + ch);
            } else {
                for(String s : ll)
                    for(char ch : hash[k])
                        cc.add(s + ch);
            }
            ll = cc;
        }
        return ll;
    }
}
```


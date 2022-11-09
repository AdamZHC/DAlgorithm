# @`LeetCode`76. 最小覆盖子串

### 思路

没有啥特别的技巧，和之前的**滑动窗口**是一个套路，只需要注意**跳出循环的条件**就可以

**窗口需要维护的值**就是有关这个**子串**的**哈希计数值**

```java
class Solution {
    char meta = 'A';
    int tot = 100;
    public String minWindow(String s, String t) {
        int m = s.length(), n = t.length();
        if(m < n)
            return "";
        int[] hash = hash(t);
        int[] ch = new int[tot];
        String ans = "";
        int cnt = 0x7fffffff;
        char[] chs = s.toCharArray();
        for(int l = 0, r = 0; l < m; ++l) {
            while(r < m && !chk(ch, hash)) 
                ch[chs[r++] - meta]++;
            if(chk(ch, hash) && r - l < cnt) {
                cnt = r - l;
                ans = s.substring(l, r);
            }
            ch[chs[l] - meta]--;
        }
        return ans;
    }
    int[] hash(String t) {
        int[] hash = new int[tot];
        char[] chs = t.toCharArray();
        for(char ch : chs)
            hash[ch - meta]++;
        return hash;
    }
    boolean chk(int[] a, int[] b) {
        for(int i = 0; i < tot; ++i)
            if(a[i] < b[i])
                return false;
        return true;
    }
}
```


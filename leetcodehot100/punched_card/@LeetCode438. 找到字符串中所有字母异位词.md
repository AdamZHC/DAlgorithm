# @`LeetCode`438. 找到字符串中所有字母异位词

### 思路

使用一个变量维护和`p`串**相差的值**`ff`，同时根据此时窗口的哈希值和`p`串的**哈希值进行比较维护**`ff`

### 代码

```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int[] ph = hash(p);
        int n = p.length(), m = s.length();
        char[] chs = s.toCharArray();
        if(n > m)
            return new ArrayList<>();

        int[] ch = new int[26];
        for(int i = 0; i < n; ++i) 
            ch[chs[i] - 'a']++;
        
        int ff = 0;
        for(int i = 0; i < 26; ++i)
            ff += Math.abs(ph[i] - ch[i]);
        if(ff == 0)
            ans.add(0);
        for(int j = 0; j < m - n; ++j) {
            int tl = chs[j] - 'a';
            ff += ch[tl] <= ph[tl] ? 1 : -1;
            ch[tl]--;
            int hl = chs[n + j] - 'a';
            ff += ch[hl] >= ph[hl] ? 1 : -1;
            ch[hl]++;
            if(ff == 0)
                ans.add(j + 1);
        }
        return ans;
    }
    int[] hash(String p) {
        int[] hash = new int[26];
        char[] chs = p.toCharArray();
        for(char ch : chs)
            hash[ch - 'a']++;
        return hash;
    }
}
```


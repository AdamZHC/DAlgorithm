# @`LeetCode`49. 字母异位词分组

### 思路

这个题目关键点在于**构建`hash`函数**，也就是某组字符串分组的原因，如果说**这组字符串可以被表示为一种形式**那就没有问题，朴素地来说，我们可以选择使用对**该组进行排序**

我这里提供的思路就是**用`hash`计数的思想**，如果超出了10的话，因为有**`ascii`**码所以说也会变成一位字符，所以说**该数据范围下的字符串都可以表示为一个长为26位的字符串**

### 代码

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        //关键在于hash函数的构造
        Map<String, List<String>> map = new HashMap<>();
        for(String s : strs) {
            String k = getKey(s);
            List<String> v = map.get(k);
            if(v == null) {
                List<String> e = new ArrayList<>();
                e.add(s);
                map.put(k, e);
            } else {
                v.add(s);
            }
        }
        List<List<String>> ans = new ArrayList<>();
        for(String key : map.keySet())
            ans.add(map.get(key));
        return ans;
    }
    String getKey(String s) {
        int[] hash = new int[26];
        char[] chs = s.toCharArray();
        for(char ch : chs)
            hash[ch - 'a']++;
        StringBuilder res = new StringBuilder("");
        for(int i = 0; i < 26; ++i)
            res.append((char)('0' + hash[i]));
        return res.toString();
    }
}
```


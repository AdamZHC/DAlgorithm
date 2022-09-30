# @Trie前缀树

#### （一）问题解决：

字典树可以实现很多关于字符串前缀和词频统计的功能实现

#### （二）思路及代码实现：

1）**思路很简单**，故名思义，就是通过前缀的每次字符存在树上，另外代码实现就是使用类的思想并且成员变量就是HashMap或者数组，都是需要索引+值来实现的，数组的索引就是通过1，2，3这样的关系来实现得到的，并不会显示地存字符，所以往往字符其实没有存在树上，而是存在了父子相连的边上，因此会有这样一个**很重要的问题**（也是我之前**犯过**的问题）就是说如果存在边上的话，应当是其边的末尾来存是否是一个单词的结束，**也就是说在insert函数的实现过程中，最后循环结束之后，跳出循环体，这样才把temp.isWord = true，最后才实现这样的操作，没有在循环体内实现（i == s.length() - 1）**

2）因为是在插入的过程中只涉及到当前字符，不需要关注其它的字符，所以这里实现的减治法，而不是完全分治法，如果是减治法的情况下，仿照二分查找直接利用循环实现就可以

3）

```java
class Trie {
    final int total = 26;
    Trie[] children;
    boolean isWord;
    public Trie() {
        children = new Trie[total];
        isWord = false;
    }
    
    public void insert(String word) {
        Trie temp = this;
        for(int i = 0; i < word.length(); ++i) {
            int ch = word.charAt(i) - 'a';
            if(temp.children[ch] == null) temp.children[ch] = new Trie();
            temp = temp.children[ch];
        }
        temp.isWord = true;
    }
    
    public boolean search(String word) {
        boolean flag = true;
        Trie temp = this;
        for(int i = 0; i < word.length(); ++i) {
            int ch = word.charAt(i) - 'a';
            if(temp.children[ch] == null){
                flag = false;
                break;
            }
            //注意这里是当前对象的isWord
            temp = temp.children[ch];
        }
        return flag ? temp.isWord : flag;
    }
    
    public boolean startsWith(String prefix) {
        boolean flag = true;
        Trie temp = this;
        for(int i = 0; i < prefix.length(); ++i) {
            int ch = prefix.charAt(i) - 'a';
            if(temp.children[ch] == null){
                flag = false;
                break;
            }
            temp = temp.children[ch];
        }
        return flag;
    }
}
        //首先初始化这个字典树
        //为什么可以不使用递归，这是因为这里用到的思想是减治法，而不是完全的分治法
        //减治法可以做到只向一边递归，不需要考虑其它的，这和二分查找的思路是一样的
        //注意这样的思路是可以完成的，因为是减治，不需要递归的左右实现，只完成一边的话
        //只需要用循环结构就可以了
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
```


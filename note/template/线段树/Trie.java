package template.线段树;

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
package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode211 {
    public static void main(String[] args) {
        WordDictionary trie = new WordDictionary();
        trie.addWord("bad");
        trie.addWord("ba");
        trie.search("ba");
    }
}
class WordDictionary {
    //Trie字典树的思路
    //这个isTrie是偏向于上一层的思路
    boolean isTrie;
    Map<Character, WordDictionary> children;
    public WordDictionary() {
        isTrie = false;
        children = new HashMap<>();
    }
    //应当是递归创建的过程，每次去创建子串的字典树
    public void addWord(String word) {
        if(word.equals("")){
            isTrie = true;
            return;
        }
        //假设现在没有"."创建的过程
        WordDictionary child = new WordDictionary();
        child.addWord(word.substring(1, word.length()));
        if(children.get(word.charAt(0)) == null){
            children.put(word.charAt(0), child);
        }
    }

    public boolean search(String word) {
        char ch = word.equals("") ? '#' : word.charAt(0);
        if(children.get(ch) == null) return isTrie;
        return children.get(ch).search(word.substring(1, word.length()));
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
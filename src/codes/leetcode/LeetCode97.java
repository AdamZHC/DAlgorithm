package codes.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class LeetCode97 {
    public static void main(String[] args) {
        new Solution97().isInterleave("aabcc","dbbca", "aadbbcbcac");

    }
}
class Solution97 {
    public boolean isInterleave(String s1, String s2, String s3) {
        int p1 = 0;
        int p2 = 0;
        boolean ans = true;
        for(int i = 0; i < s3.length(); ++i) {
            if(p1 < s1.length() && s3.charAt(i) == s1.charAt(p1)) p1++;
            else if(p2 < s2.length() && s3.charAt(i) == s2.charAt(p2)) p2++;
            else ans = false;
        }
        return ans;
    }
}
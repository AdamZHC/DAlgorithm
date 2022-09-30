package codes.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode367 {
    public static void main(String[] args) {
        new Solution367().isPerfectSquare(4);
        List<Integer> list = new ArrayList<>();
    }
}
class Solution367 {
    public boolean isPerfectSquare(int num) {
        return isPerfectSquareMulti(0, num);
    }
    public boolean isPerfectSquareMulti(long root, int num) {
        //倍增
        if(root * root == num || num == 1) return true;
        long curroot = 1;
        boolean flag = true;
        while((curroot + root) * (curroot + root)  <= num){
            flag = false;
            curroot = curroot << 1;
        }
        if(flag) return false;
        return isPerfectSquareMulti((curroot >> 1) + root, num);
    }
}
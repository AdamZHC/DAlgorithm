package leetcode;

import java.util.Stack;

public class LeetCode1221 {
    public static void main(String[] args){
        new Solution13().balancedStringSplit("RLRRLLRLRL");
    }
}
class Solution13 {
    public static int balancedStringSplit(String s) {
        Stack<Character> stack = new Stack <Character>();
        int flag = 0,cnt = 0;
        for(int i = 0; i < s.length() ; i ++){
            if(i == 0)stack.push(s.charAt(i));
            else{
                //果然栈都要循环
                while(stack.size() != 0 && stack.peek() != s.charAt(i) ){
                    stack.pop();
                    flag = 1;
                    i ++;
                }
                if(flag == 0)stack.push(s.charAt(i));
                if(stack.size() == 0) cnt += flag;
                flag = 0;
            }
        }
        return cnt;
    }
}
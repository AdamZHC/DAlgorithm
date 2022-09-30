package leetcode;

public class LeetCode8 {
    public static void main(String[] args) {
        new Solution8().myAtoi("21474836460");
    }
}
class Solution8 {
    public int myAtoi(String s) {
        if(s.length() == 0) return 0;
        //遍历
        //排除前导空格
        int i = 0;
        while(i < s.length() && s.charAt(i) == ' ') ++i;
        //'0' ---> 48
        //排除全部为空格的情况
        if(i == s.length()) return 0;
        //开始正式处理
        //处理符号
        if(!(s.charAt(i) == '+' || s.charAt(i) == '-' || (s.charAt(i) >= '0' && s.charAt(i) <= '9')))
            return 0;
        boolean flag = false;
        if(s.charAt(i) == '-') flag = true;
        //排除正负号的影响
        if(s.charAt(i) == '+' || s.charAt(i) == '-') ++i;
        //排除前导零的影响
        while(i < s.length() && s.charAt(i) == '0' ) ++i;
        if(i == s.length()) return 0;
        //正式处理数字
        long num = 0;
        while(i < s.length()){
            if(num <= -2147483648){
                //对于超出范围的数字直接处理
                if(flag){
                    return -2147483648;
                }else{
                    return 2147483647;
                }
            }
            char ch = s.charAt(i++);
            if(ch < '0' || ch > '9'){
                //对于已经解析的数字进行处理
                return flag ? (int)num : -(int)num;
            }
            //这边没有 += 的符号
            num = num * 10 + (long)('0'- ch);
        }
        return flag ? (int)num : -(int)num;
    }
}
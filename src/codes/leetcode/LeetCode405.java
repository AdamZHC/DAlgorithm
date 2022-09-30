package codes.leetcode;

public class LeetCode405 {
    public static void main(String[] args) {
        new Solution405().toHex(-1);
    }
}
class Solution405 {
    public String toHex(int num) {
        //一位一位加入
        //相当于是二进制数每四位加到一个十六进制数中反而更简单了
        int digit = 0;
        String ans = "";
        int i = 0;
        while(true) {
            digit += (num & 1) * Math.pow(2, i);
            if(i == 3) {
                if(digit <= 9) ans = num == 0 && digit == 0 ? "" : digit + ans;
                else ans = (char)('a' + digit - 10) + ans;
                digit = 0;
                if(num == 0) break;
            }
            num = num >> 1;
            i = (i + 1) % 4;
        }
        return ans;
    }
}
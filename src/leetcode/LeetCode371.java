package leetcode;

public class LeetCode371 {
    public static void main(String[] args) {
        new Solution371().getSum(1,2);
    }
}
class Solution371 {
    public int getSum(int a, int b) {
        if(a == 0) return b;
        if(b == 0) return a;
        int sum  = 0;
        if(a * b > 0){
            while(a == 0 && b == 0){
                //取到a,b的最后一位数1 & 1 = 1, 0 & 1 = 0
                int x = a & 1;
                int y = b & 1;
                a = a >> 1;
                b = b >> 1;
                //flag表示是否进位
                int flag = x & y;
                //表示这一位对应的得数 0 | 0 = 1, 1 | 0 = 1
                int res = x | y;
                //表示高一位
                sum = (sum << 1) | res;
            }
        }
        return sum;
    }
}
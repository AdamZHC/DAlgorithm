package codes.leetcode;

public class LeetCode29 {
    public static void main(String[] args) {
        new Solution29().divide(2147483647, 4);
    }
}
class Solution29 {
    public int divide(int dividend, int divisor) {
        //使用倍增的思想，也就是把暴力枚举优化一下，找到向下取整的数
        //应当是有递归的思路，因为需要找到一个倍增的上界之后再去获取下一个倍增的上界
        //一层递归需要获取，该轮对应的结果，当然是2的幂次
        //防止溢出的处理
        if(dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        if(divisor == 1) return dividend;
        if(divisor == -1) return ~ dividend + 1;
        //如果被除数为0，返回零
        if(dividend == 0) return 0;
        //如果异号的话，也就是一个为正一个为负
//        boolean flag = true;
//        if(dividend * divisor < 0) flag = false;
        //各自取整
        //这个思路好，既然正数不可以处理，因为范围小不可取，那就转化为负数运算！！！
        //这样就是避免了正数的范围过小造成的问题，无形中处理了问题
        divisor  = (divisor == -2147483648) ? -2147483648 : - Math.abs(divisor);
        dividend = (dividend == -2147483648) ? -2147483648 : - Math.abs(dividend);
        //递归的边界问题
        if(dividend > divisor) return 0;
        if(dividend == divisor) return 1;
        int temp = divisor;
        int ans = 1;
        //注意可以特殊地对里面的一部分做处理，给出特定地数
        while(temp << 1 >= dividend) {
            ans = ans << 1;
            temp = temp << 1;
        }
        int finalAns = ans + divide(dividend - temp, divisor);
        return finalAns ;
        //题目说环境只能存2^32的整数，所以最优的处理就是使用int
        //但是我还达不到那种水平，那就算了，就是使用long来处理数据会发生溢出的问题吧！！
        //用long存就可以
    }
}
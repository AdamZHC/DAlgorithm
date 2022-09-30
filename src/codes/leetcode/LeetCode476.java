package codes.leetcode;

public class LeetCode476 {
    public static void main(String[] args) {
        new Solution476().findComplement(9);
    }
}
class Solution476 {
    public int findComplement(int num) {
        //异或 -- 1 ^ 1 = 0 -- 1 ^ 0 = 1
        int temp = ~ num;
        int ans = 0;
        while(num != 1){
                        
            ans = (ans << 1) ^ (temp & 1);
            num = num >> 1;
            temp = temp >> 1;
        }
        return ans;
    }
}
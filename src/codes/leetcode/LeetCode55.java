package codes.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeetCode55 {
    public static void main(String[] args){
        int[] a = {3,2,1,0,4};
        new Solution55().canJump2(a);
    }
}
class Solution55 {
    public boolean canJump(int[] nums) {
        //显然是存在性dp,dp要看最后的条件是什么是否可以到达
        //注意有是否---存在性dp
        //注意有最大最小---一般的dp
        //而且dp是需要每一个范围都要遍历到！！！！！！！！
        //即是(0~n)都要遍历到
        int n = nums.length;
        boolean []dp = new boolean[n];
        //初始化
        dp[0] = true;
        //开始动态规划
        for(int i = 1; i < n; i ++){
            for(int j = 1; j <= i ; j ++){
                if(nums[i - j] >= i - j){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n - 1];
    }
    public boolean canJump2(int[] nums) {
        //最大可以到的距离

        //当前走到的步数
        int step = 0;
        int max = nums[step];
        int cnt = 0;
        while(step < nums.length && max < nums.length - 1){
            int tempStep = cnt++ == 0 ? 0 : nums[step] + step;
            step = max;
            //tempStep用来保存当前一个周期内不变的步数，这个需要控制跳出循环
            boolean flag = true;
            while(tempStep < step && step < nums.length - 1){
                if(nums[step] + step > max){
                    max = nums[step] + step;
                    flag = false;
                }
                step--;
            }
            if(flag) break;
        }
        return max >= nums.length - 1;
    }

//贪心去维护最大的距离 --
}
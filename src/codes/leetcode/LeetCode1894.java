package codes.leetcode;

import java.util.HashSet;
import java.util.Set;

public class LeetCode1894 {
    public static void main(String[] args){
        int[] a = {30,76,46,74,34,12,1,82,25,28,63,29,60,76,98,20,40,32,76,26,71};

//        new Solution1894().chalkReplacer(a,346237330);
        Set<Integer> set = new HashSet();
        set.add(1);
        System.out.print(set.isEmpty());
    }
}
class Solution1894 {
    public int chalkReplacer(int[] chalk, int k) {
        //贪心算法：现在找到最近的一整组(遍历 + 循环比较)
        int chalkSum = 0;
        for(int i = 0; i < chalk.length ;i ++){
            chalkSum += chalk[i];
        }
        int curSum = 0, mult = 0;
        while(curSum <= k){
            curSum = chalkSum * (++ mult);
        }
        int flag = curSum - k >= k - chalkSum * (mult - 1) ? 1 : -1;
        int rest = Math.abs(k - (flag > 0 ? chalkSum * (mult - 1) : curSum));
        int cur = flag > 0 ? 0 : chalk.length - 1 , sum = 0;
        while (sum < rest  ){
            sum += chalk[cur];
            cur += flag;
        }
        return  cur ;
    }
}

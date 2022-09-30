package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BoatDP {
    public static void main(String[] args){
        Solution3 s = new Solution3();
        int[] people = {21,40,16,24,30};
        int limit  =50;
        s.numRescueBoats(people,limit);
    }
}
class Solution3 {
    public int numRescueBoats(int[] people, int limit) {
        //dp加哈希表的思路:
        //dp[i] 表示第i个人被载之后的最小船数
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        int[] dp = new int[people.length];
        //开始动态规划-----思路就是：
        //一个一个去找，如果没满的话就把剩余的人数和放到哈希表中
        //状态转移方程也需要对哈希表进行操作
        dp[0] = 1;
        if(limit - people[0] > 0) map.put(limit-people[0],1);
        for(int i = 1 ; i < people.length ; i++){
            System.out.println(map);
            if(people[i] == limit){
                dp[i] = dp[i-1] + 1;
            }else{
                boolean flag = false;
                int num =  people[i];
                while(true){
                    //如果对应的剩下来的不为空说明有空余的位置
                    if( map.get(num) != null ){
                        //如果只有一个的话移除该键,否则减一
                        if(map.get(num) == 0) break;
                        map.put(num,map.get(num)-1);
                        flag = true;
                        break;
                    }
                    if(map.get(num) == null){
                        num++;
                    }
                    if(num == limit){
                        break;
                    }
                }
                if(flag == true){
                    dp[i] = dp[i-1];
                }else{
                    dp[i] = dp[i-1] + 1;
                    if(map.get(limit - people[i]) != null){
                        map.put(limit - people[i],map.get(limit -people[i]) + 1);
                    }else{
                        map.put(limit - people[i],1);
                    }
                }

            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[people.length - 1];
    }
}
package codes.leetcode;

public class LeetCode1438 {
    public static void main(String[] args) {
        int[] nums = {10,1,2,6,2};
        new Solution1438().longestSubarray(nums, 0);
    }
}
class Solution1438 {
    public int longestSubarray(int[] nums, int limit) {
        //滑动窗口 + 双指针
        //每次去维护最大绝对差
        int left = 0, right = 0, ans = 0;
        //offset在滑窗生成过程中，去维护最大绝对差，offset[0]表示最大值，offset[1]表示最小值
        //offset[2]表示当前位置的绝对差
        //生成过程中如果满足条件向右移动,当生成过程不满足的时候，right - left更新ans
        //问题的细节还是要想清楚，不能马虎
        int[] offset = new int[3];
        offset[0] = Integer.MIN_VALUE;
        offset[1] = Integer.MAX_VALUE;
        while(right < nums.length - 1){
            if(left == right){
                right ++;
                update(nums[left], offset);
                update(nums[right], offset);
                if(offset[2] <= limit) ans = Math.max(right - left + 1, ans);
                continue;
            }
            //如果是左侧滑动的情况比较复杂，需要去逐个更新
            if(offset[2] > limit){
                int temp = left + 1;
                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;
                offset[0] = Integer.MIN_VALUE;
                offset[1] = Integer.MAX_VALUE;
                //在这里去更新还需要循环
                while(temp <= right){
                    //如果说找到的情况下，那就说明不需要改变offset里面的东西，因为这个子序列是存在的
                    max = nums[temp] > max ? nums[temp] : max;
                    min = nums[temp] < min ? nums[temp] : min;
                    temp ++;
                }
                update(max, offset);
                update(min, offset);
                left ++;
            }else{
                right ++;
                update(nums[right], offset);
                if(offset[2] <= limit) ans = Math.max(right - left + 1, ans);
            }
        }
        return ans;
    }
    public void update(int num, int[] offset){
        offset[0] = (num >= offset[0] ? num : offset[0]);
        offset[1] = (num <= offset[1] ? num : offset[1]);
        offset[2] = offset[0] - offset[1];
    }
}
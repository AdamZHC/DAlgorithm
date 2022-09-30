package lanqiao;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class C5 {
    public static void main(String[] args) {

    }
}
class Solution {
    //先单个入窗
    //允许第一个条件超出预定范围
    //并且维护的窗口满足的范围是含l不含r
    //就是说是这样的[l , r)
    //在初始时刻都是固定的也就是l = 0, r = 0时表示窗口为空(约定的)
    public int numSubarrayProductLessThanK(int[] nums, int k) {


        //滑动窗口
        int n = nums.length;
        int l = 0, r = 0;
        int ans = 0, m = 1;
        while(r < n) {
            if(m < k){
                k *= nums[r++];
                ans++;
            }
            while(m > k) {
                k /= nums[l++];
            }
        }
        return ans + r - l;
    }
}
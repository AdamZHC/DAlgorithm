package codes.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode442 {
    public static void main(String[] args){
        int[] nums = {10,2,5,10,9,1,1,4,3,7};
        System.out.println(new Solution442().findDuplicates(nums));
    }
}
class Solution442 {
    public List<Integer> findDuplicates(int[] nums) {
        //原地哈希，完全可以一步一步直接扔进哈希表里面
        //这样可以排除一定的误差
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < nums.length; i ++){
            nums[Math.abs(nums[i]) - 1] *= -1;
            if(nums[Math.abs(nums[i]) - 1] > 0){
                list.add(Math.abs(nums[i]));
            }
        }
        return list;
    }
}
package leetcode;

import java.util.*;

public class CountSum {
    public static void main(String[] args){
        //两层循环简单模拟
//        for(int len = 1; len < 11; len++){
//            int[] nums = new int[(len+1)/2];
//            for(int  i = 1; i <= len ; i += 2){
//                int min = Math.min(len+1-i, i);
//                for(int j = 1; j <= min; j++){
//                    nums[j-1] += j;
//                }
//                for(int j = min; j < (len+1)/2 ;j++){
//                    nums[j] += min;
//                }
//            }
//            System.out.println("len: "+len+" <-> "+(len+1)/2+" : "+Arrays.toString(nums));
//        }
        int[] arr = {1,2,3,4,5};
        int len = 5;
        int key = (len + 1)/2;
        int[] nums = new int[key];
        for(int i = 0; i < key ;i ++){
            if(i == 0) nums[i] = key;
            else{
                if(len % 2 == 0) nums[i] = nums[i-1] + key - i;
                else nums[i] = nums[i-1] + key -(i % 2 == 0 ? i : i + 1);
            }
        }
//        System.out.println("len: "+len+" <-> "+(len+1)/2+" : "+Arrays.toString(nums));
        int sum = 0;
        for(int i = 0; i < len ; i++){
            sum += arr[i]*nums[i > key - 1 ? len -1 -i : i];
        }
        System.out.println(sum);
        Map<Integer,Integer> map = new HashMap<>();
    }
}

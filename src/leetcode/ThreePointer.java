package leetcode;

import java.util.Arrays;

public class ThreePointer {
    public static void main(String[] args){
        Solution4 s = new Solution4();
        int[] nums = {1,2,3};
        int k = 2;
        System.out.println(Arrays.toString(s.rotate(nums,2)));
    }
}
class Solution4 {
    public int[] rotate(int[] nums, int k) {
        //利用三指针，三个指针一个指向数组的头，一个指向交换的位置，另一个是位置的前一个
        if(nums.length == 1){
            return nums;
        }
        int rear = Math.abs(nums.length - k);
        int mid  = nums.length - rear ;
        int front  = 0;
        for(int i = 0 ; i < k; i++){
            int temp= nums[(rear + i)%nums.length];
            nums[(rear + i)%nums.length] = nums[(mid + i)%nums.length];
            nums[(mid + i)%nums.length ] = nums[(front + i)%nums.length];
            nums[(front + i)%nums.length] = temp;
        }
        return nums;
    }
}
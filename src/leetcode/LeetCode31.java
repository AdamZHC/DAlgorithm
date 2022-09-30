package leetcode;

public class LeetCode31 {
    public static void main(String[] args) {
        int []nums = {3,2,1};
        new Solution31().nextPermutation(nums);
    }
}
class Solution31 {
    public void nextPermutation(int[] nums) {
        nextPermutationCore(nums, 0);
    }
    void nextPermutationCore(int[] nums, int idx){
        int i = nums.length - 1;
        for(; i > idx; i-- ) {
            if(nums[i - 1] < nums[i]){
                nums[i - 1] = nums[i - 1] ^ nums[i];
                nums[i] = nums[i - 1] ^ nums[i];
                nums[i - 1] = nums[i - 1] ^ nums[i];
                nextPermutationCore(nums, i);
                break;
            }
        }
        if(i == idx){
            //这里实现逆置
            int len = nums.length - idx;
            if(len == 1) return;
            for(int j = 0 ; j < len / 2; j++ ) {
                nums[len - j + idx - 1] = nums[len - j + idx - 1] ^ nums[idx + j];
                nums[idx + j] = nums[len - j + idx - 1] ^ nums[idx + j];
                nums[len - j + idx - 1] = nums[len - j + idx - 1] ^ nums[idx + j];
            }
        }
    }
}
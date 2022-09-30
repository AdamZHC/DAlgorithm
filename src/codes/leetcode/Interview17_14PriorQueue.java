package codes.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 关于容器比较器的重写，使用匿名内部类重写Comparator方法
 * 传入的泛型和后面的泛型保持一致
 * 或者可以在泛型的类里面来自定义比较操作
 */
public class Interview17_14PriorQueue {
    public static void main(String[] args){
        PriorityQueue<Integer> p = new PriorityQueue(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        int[] a = {2,7,7,15,9,8};
        twoSum(a,14);
    }
    public static int[] twoSum(int[] nums, int target) {
        int ans[] = new int[2];
        //使用优先队列去维护整个数组
        PriorityQueue <Integer> p = new PriorityQueue<Integer>();
        int sort[] =new int[nums.length];
        for(int i = 0; i < nums.length ; i ++){
            p.add(nums[i]);
        }
        for(int i = 0; i < nums.length ; i ++){
            sort[i] = p.remove();
        }
        // return sort;
        // 二分查找 -- 不可取
        // 双指针
        int left = 0,right = nums.length - 1;
        while(true){
            if(sort[left] + sort[right] == target) break;
            if(sort[left] + sort[right] > target) right --;
            else left ++;
        }
        //找到对应的下标
        for(int i = 0 ; i < nums.length ; i++){
            if(nums[i] == sort[left]){
                ans[0] = i;
                continue;
            }
            if(nums[i] == sort[right]){
                ans[1] = i;
            }
        }
        return ans;
    }

}
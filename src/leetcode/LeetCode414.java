package leetcode;
import java.util.*;
public class LeetCode414 {
    public static void main(String[] args) {
        int[] nums = {-2, 1, 1};
        new Solution414().thirdMax(nums);
    }
}

class Solution414 {
    public int thirdMax(int[] nums) {
        //初始化优先队列
        PriorityQueue<Integer> p = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                return o1 - o2;
            }
        });
        //假设这就是前K大的数
        Set<Integer> set = new HashSet<>();
        for(int i : nums){
            set.add(i);
        }
        int cnt = 3;
        for(int e: set){
            if((cnt--) > 0){
                p.add(e);
            }else{
                if(e > p.peek()){
                    p.remove();
                    p.add(e);
                }
            }
        }
        if(cnt == 1){
            p.remove();
        }
        return p.peek();
    }
}
package codes.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class LeetCode659 {
    public static void main(String[] args) {
        int sample[] = {1,1,1,4,13,30,32,34,46,60};
        new Solution659().isPossible(sample);
    }
}
class Solution659 {
    public boolean isPossible(int[] nums) {
        if(nums.length < 3) return false;
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < nums.length; ++i) {
            list.add(nums[i]);
        }
        PriorityQueue<Integer> outpq = new PriorityQueue<>();
        while(!list.isEmpty()) {
            PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            int index = 0;
            while(pq.size() < 3 && index < list.size()){
                if(pq.isEmpty()){
                    pq.add(list.remove(index));
                }else{
                    if(list.get(index) > pq.peek()){
                        pq.add(list.remove(index));
                    }else{
                        index++;
                    }
                }
            }
            if(pq.size() < 3){
                int element = pq.peek();
                while(pq.size() != 1) pq.remove();
                if(outpq.isEmpty() || pq.peek() <= outpq.peek()) return false;
                else{
                    outpq.remove();
                    outpq.add(element);
                }
            }else{
                outpq.add(pq.peek());
            }
        }
        return true;
    }
}
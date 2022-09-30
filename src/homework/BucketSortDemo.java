package homework;


import java.util.*;

public class BucketSortDemo {
    public static void main(String[] args) {

    }

    /**
     *
     * @param nums: 待排序数组
     * @param digit:最大值的位数
     * @return
     */
    public static int[] bucketSort(int[] nums, int digit){
        //桶排序
        //这里面是null,没有实际地初始化
        Queue<Integer>[] buckets = new Queue[10];
        //初始化
        for(int i = 0; i < 10; i ++){
            buckets[i] = new ArrayDeque<>();
        }
        for(int i = 1; i <= digit; i++){
            for(int element : nums){
                buckets[element % (int)Math.pow(10, i)].add(element);
            }
            int idx = 0;
            for( Queue<Integer> bucket : buckets){
                while(! bucket.isEmpty()){
                    nums[idx ++] = bucket.remove();
                }
            }
        }
        return nums;
    }
    public void init(String[] data, int[] nums){
        Map<Integer, String> map = new HashMap<>();
        for(int i = 0; i < data.length; i ++){
            map.put(nums[i], data[i]);
        }
    }
    public int[] countSort(int[] nums){
        int[] temp = new int[100];
        for(int i = 0; i < nums.length; i ++){
            temp[i] ++;
        }
        int cnt = 0;
        for(int i = 0; i < temp.length; i ++){
            if(temp[i] == 0) continue;
            while(temp[i] -- == 0){
                nums[cnt ++] = i;
            }
        }
        return nums;
    }
}

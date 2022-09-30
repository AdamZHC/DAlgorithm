package leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LeetCode598 {
    public static void main(String[] args) {
        int[][] ops = {
                {2,2},
                {3,3}
        };
        new Solution598().maxCount(3,3,ops);
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
    }
}
class Solution598 {
    public int maxCount(int m, int n, int[][] ops) {
        //维护一个差分矩阵
        //operation就是一个(循环)操作
        //如果使用原始模拟的话，那就是一个循环操作
        //使用差分的思想可以降低到O(n)
        //初始化一个大小为(m + 2) * (n + 2)的差分矩阵
        int[][] diff = new int[m + 2][n + 2];
        //生成差分矩阵
        for(int[] op: ops) {
            diff[0][0] += 1;
            diff[op[0] + 1][0] += -1;
            diff[0][op[1] + 1] += -1;
            diff[op[0] + 1][op[1] + 1] += 1;
        }
        int max = Integer.MIN_VALUE;
        int cnt = 1;
        //还原矩阵
        for(int j = 0; j < n + 2; ++j) {
            for(int i = 1; i < m + 2; ++i) {
                diff[i][j] += diff[i - 1][j];
            }
        }
        for(int i = 0; i < m + 2; ++i) {
            for(int j = 1; j < n + 2; ++j) {
                diff[i][j] += diff[i][j - 1];
                if(diff[i][j] > max && i >= 1){
                    max = diff[i][j];
                    cnt = 1;
                }else if(diff[i][j] == max && i >= 1){
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
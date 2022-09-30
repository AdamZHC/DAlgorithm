package lanqiao;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class C6 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] tree = new int[n];
        for(int i = 0; i < n ; ++i) {
            tree[i] = in.nextInt();
        }
        //BFS
        int ansSum = 0;
        int ans = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{tree[0], 0, 0});
        while(!q.isEmpty()) {
            int sum = 0;
            int size = q.size();
            int layer = q.peek()[2];
            for(int i = 0; i < size; ++i) {
                int[] node = q.remove();
                sum += node[0];
                if(2 * node[1] + 1 < n){
                    q.add(new int[]{tree[2 * node[1] + 1], 2 * node[1] + 1, node[2] + 1});
                }
                if(2 * node[1] + 2 < n){
                    q.add(new int[]{tree[2 * node[1] + 2], 2 * node[1] + 2, node[2] + 1});
                }
            }
            if(sum >= ansSum){
                ansSum = ans;
                ans = layer;
            }
        }
        System.out.println(ans);
    }
}

package review;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Prim {
    //同时也是属于使用堆优化
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] graph = new int[n + 1][n + 1];
        //初始化图
        for(int i = 1; i <= n; ++i) {
            for(int j = 1; j <= n; ++j) {
                graph[i][j] = 0x7fffffff;
            }
        }
        for(int i = 0; i < m; ++i){
            int x = in.nextInt();
            int y = in.nextInt();
            int z = in.nextInt();
            graph[x][y] = Math.min(graph[x][y], z);
            graph[y][x] = Math.min(graph[y][x], z);
        }
        //使用堆优化
        //维护数组——true表示已经加入最小生成树中
        boolean[] vis = new boolean[n + 1];
        int ans = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        pq.add(new int[]{1, 1, 0});
        int cnt = n;
        while(cnt > 0 && !pq.isEmpty()) {
            int[] v = pq.poll();
            if(!vis[v[0]]){
                vis[v[0]] = true;
                ans += v[2];
                cnt--;
            }
            for(int i = 1; i <= n; ++i) {
                if(graph[v[0]][i] != 0x7fffffff && !vis[i]){
                    //注意这里的添加和对应的Dijkstra算法就有区别
                    //Dijkstra:edge[2] + graph[edge[0]][i]
                    //Prim:graph[v[0]][i]
                    pq.add(new int[]{i, v[0], graph[v[0]][i]});
                }
            }
        }
    }

}

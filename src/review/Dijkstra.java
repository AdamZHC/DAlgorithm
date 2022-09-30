package review;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {
    //涉及到最短路（有路径的概念——有向，另外是有权值的，最短路）——有向带权图
    //莫名觉得这个算法很简单——使用堆优化——应用队列的思想就完了呗，然后就是维护若干个数组
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt();
        //构造邻接矩阵
        int[][] graph = new int[n + 1][n + 1];
        for(int i = 1; i <= n; ++i) {
            for(int j = 1; j <= n; ++j) {
                graph[i][j] = 0x7fffffff;
            }
        }
        for(int i = 0; i < m ; ++i) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            graph[u][v] = Math.min(w, graph[u][v]);
        }
        for(int[] g : graph) {
            System.out.println(Arrays.toString(g));
        }
        //dis[]表示单源最短路的距离，vis[]表示是否访问过， path[]表示最短路的路径
        //先标记，再入队
        int[] dis = new int[n + 1];
        boolean[] vis = new boolean[n + 1];
        vis[s] = true;
//        int[] path = new int[n + 1];
        //用数组表示结构体 v[0]是结点编号，v[1]表示从个编号得到的， v[2]表示到起点的距离
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        pq.add(new int[]{s, s, 0});
        //开始广搜

        /*
        之前的vis[]是用来表示该顶点是否入过队，但是堆优化的Dijkstra的vis[]是指是否成为单源最短路
        因为如果使用之前队列的vis[]思路的话，就会导致新的应当被更新的顶点没法入队，从而出现问题
        这里是指vis[]是指是否成为单源最短路， 之前vis[]的棋盘算法的搜索思路不同
        这里需要在poll()之后，找到对应的最短路之后标记——出队列后标记，之前的是——入队之前标记
        */
        int cnt = n;
        while(cnt != 0 && !pq.isEmpty()) {
            //获取当前的堆顶元素
            //如果是访问过了，这表示在队列里面的元素是在之前以及加入过最短路集合——这也就是之前讨论过的，思考过的那种情况
            int[] edge = pq.poll();
            pq.forEach(e -> {
                    System.out.println(Arrays.toString(e));
            });
            if(!vis[edge[0]]){
                dis[edge[0]] = edge[2];
//                path[v[0]] = v[1];
                vis[edge[0]] = true;
                cnt--;
            }
            for(int i = 1; i <= n; ++i) {
                if(graph[edge[0]][i] != 0x7fffffff && !vis[i]){
                    pq.add(new int[]{i, edge[0], edge[2] + graph[edge[0]][i]});
                }
            }
        }
        pq.forEach(e -> {
            System.out.println(Arrays.toString(e));
        });
        for(int  i = 1; i <= n; ++i){
            System.out.printf("%d ",dis[i]);
        }

    }
}

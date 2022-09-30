package review;

import java.util.Arrays;

public class Floyd {
    public static void main(String[] args){
        int[][]graph = {
                {2, 5},
                {3},
                {0, 4, 5},
                {1, 4, 5},
                {2, 3},
                {0, 2, 3}
        };
        int n = graph.length;
        int[][] dis = new int[n][n];
        int[][] pre = new int[n][n];
        for(int i = 0; i < n; ++i){
            Arrays.fill(dis[i], 10000);
            Arrays.fill(pre[i],i);
        }

        for(int i = 0; i < n; ++i)
            dis[i][i] = 0;
        for(int i = 0; i < n; ++i){
            int m = graph[i].length;
            for(int j = 0; j < m; ++j)
                dis[i][graph[i][j]] = 1;
        }

        for(int k = 0; k < n; ++k)
            for(int i = 0; i < n; ++i)
                for(int j = 0; j < n; ++j)
                    if(dis[i][j] > dis[i][k] + dis[k][j]){
                        dis[i][j] = dis[i][k] + dis[k][j];
                        pre[i][j] = pre[k][j];
                    }

        for(int[] e : dis)
            System.out.println(Arrays.toString(e));

    }
}

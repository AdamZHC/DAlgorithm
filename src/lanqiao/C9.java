package lanqiao;

import java.util.*;

public class C9 {
    static int ans = 0x7fffffff;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Queue<Integer> q = new ArrayDeque<>();
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int candies[][] = new int[n][k];
        for(int i = 0; i < n ; ++i) {
            for(int j = 0; j < k; ++j) {
                candies[i][j] = in.nextInt();
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        dfs(map, candies, 0, m);
        System.out.println(ans);
    }
    //dfs
    static void dfs(Map<Integer, Integer> map, int[][] candies, int step, int m){
        if(map.size() == m){
            ans = Math.min(step, ans);
            return;
        }
        for(int i = step; i < candies.length; ++i) {
            for(int j = 0; j < candies[i].length; ++j){
                if(map.get(candies[i][j]) == null){
                    map.put(candies[i][j], 1);
                }else{
                    map.put(candies[i][j], map.get(candies[i][j]) + 1);
                }
            }
            dfs(map, candies, step + 1, m);
            for(int j = 0; j < candies[i].length; ++j){
                if(map.get(candies[i][j]) == 1){
                    map.remove(candies[i][j]);
                }else{
                    map.put(candies[i][j], map.get(candies[i][j]) - 1);
                }
            }
        }

    }
}

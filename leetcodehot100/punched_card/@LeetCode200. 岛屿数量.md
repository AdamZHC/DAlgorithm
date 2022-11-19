# @`LeetCode`200. 岛屿数量

```java
class Solution {
    final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length, ans = 0;
        boolean[][] vis = new boolean[m][n];
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(grid[i][j] == '1' && !vis[i][j]) {
                    bfs(grid, vis, i, j);
                    ans++;
                }
            }
        }
        return ans;
    }
    void bfs(char[][] grid, boolean[][] vis, int i, int j) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> q = new ArrayDeque<>();
        vis[i][j] = true;
        q.offer(new int[]{i, j});
        while(!q.isEmpty()) {
            int[] p = q.poll();
            for(int k = 0; k < 4; ++k) {
                int newX = p[0] + dir[k][0];
                int newY = p[1] + dir[k][1];
                if(chk(newX, newY, m, n) && grid[newX][newY] == '1' && !vis[newX][newY]) {
                    vis[newX][newY] = true;
                    q.offer(new int[]{newX, newY});
                }
            }
        }
    }
    boolean chk(int i, int j, int m, int n) {
        return i >= 0 && i < m && j >= 0 && j < n;
    } 
}
```


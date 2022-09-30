package luogu;


import java.util.Scanner;
import java.util.Stack;

public class P1002 {
    private static long cnt = 0;
    public static void main(String[] args) {
        String s = "asdugasoud";
        //Scanner不需要处理换行\n，每次就sc.nextInt()
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] maze = new int[m][n];
        boolean[][] vis = new boolean[m][n];
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                maze[i][j] = sc.nextInt();
            }
        }
        int[] start = new int[2];
        start[0] = sc.nextInt() - 1;
        start[1] = sc.nextInt() - 1;
        int[] end = new int[2];
        end[0] = sc.nextInt() - 1;
        end[1] = sc.nextInt() - 1;
        Stack<int[]> memo = new Stack<>();
        memo.add(start);
        vis[start[0]][start[1]] = true;
        if(maze[start[0]][start[1]] == 1) dfs(maze, vis, start, end, memo);
        if(cnt == 0) System.out.println(-1);
    }

    /**
     *
     * @param maze:对应的迷宫
     * @param start:起点 -- 也表示当前走到的位置
     * @param end:终点
     * @param memo:记忆栈，dfs往往需要一个栈来存对应的路径
     * @param vis:记录是否被访问过
     */
    static void dfs(int[][]maze, boolean[][] vis, int[] start, int[] end, Stack<int[]> memo) {
        if(start[0] == end[0] && start[1] == end[1]){
            cnt++;
            Stack<int[]> out = new Stack<>();
            while(!memo.isEmpty()){
                out.push(memo.pop());
            }
            while(!out.isEmpty()){
                memo.push(out.pop());
                if(out.isEmpty()) System.out.printf("(%d,%d)", memo.peek()[0] + 1, memo.peek()[1] + 1);
                else System.out.printf("(%d,%d)->", memo.peek()[0] + 1, memo.peek()[1] + 1);
            }
            System.out.println();
            return;
        }
        //按照顺序输出，按照左上右下的顺序的迷宫策略
        //按照我的习惯，还有防止堆栈溢出，需要在前面处理边界问题
        if(start[1] - 1 >= 0 && vis[start[0]][start[1] - 1] == false && maze[start[0]][start[1] - 1] == 1){
            vis[start[0]][start[1] - 1] = true;
            memo.push(new int[]{start[0], start[1] - 1});
            dfs(maze, vis, new int[]{start[0], start[1] - 1}, end, memo);
            vis[start[0]][start[1] - 1] = false;
            memo.pop();
        }
        if(start[0] - 1 >= 0 && vis[start[0] - 1][start[1]] == false && maze[start[0] - 1][start[1]] == 1){
            vis[start[0] - 1][start[1]] = true;
            memo.push(new int[]{start[0] - 1, start[1]});
            dfs(maze, vis, new int[]{start[0] - 1, start[1]}, end, memo);
            vis[start[0] - 1][start[1]] = false;
            memo.pop();
        }
        if(start[1] + 1 <= maze[0].length - 1 && vis[start[0]][start[1] + 1] == false && maze[start[0]][start[1] + 1] == 1){
            vis[start[0]][start[1] + 1] = true;
            memo.push(new int[]{start[0], start[1] + 1});
            dfs(maze, vis, new int[]{start[0], start[1] + 1}, end, memo);
            vis[start[0]][start[1] + 1] = false;
            memo.pop();
        }
        if(start[0] + 1 <= maze.length - 1 && vis[start[0] + 1][start[1]] == false && maze[start[0] + 1][start[1]] == 1){
            vis[start[0] + 1][start[1]] = true;
            memo.push(new int[]{start[0] + 1, start[1]});
            dfs(maze, vis, new int[]{start[0] + 1, start[1]}, end, memo);
            vis[start[0] + 1][start[1]] = false;
            memo.pop();
        }
    }
}

package luogu;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class P1219 {
    public static void main(String[] args) {
        String s = "qweqqwe";

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int x = sc.nextInt() - 1;
        int y = sc.nextInt() - 1;
        int[][]board = new int[n + 10][m + 10];
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < m; ++j) {
                board[i][j] = Integer.MAX_VALUE - 10;
            }
        }
        board[x][y] = 0;
        int[][] directions = {
                {2, 1}, {1, 2}, {2, -1}, {1, -2},{-1, 2}, {-2, 1}, {-1, -2}, {-2, -1}
        };
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y});
        while(!queue.isEmpty()){
            int[] pos = queue.remove();
            for(int[] direction : directions) {
                if(check(pos[0] + direction[0], pos[1] + direction[1], n, m) && board[pos[0] + direction[0]][pos[1] + direction[1]] > board[pos[0]][pos[1]] + 1){
                    board[pos[0] + direction[0]][pos[1] + direction[1]] = board[pos[0]][pos[1]] + 1;
                    queue.add(new int[]{pos[0] + direction[0], pos[1] + direction[1]});
                }
            }
        }
        //dfs(board, vis, x, y, directions);
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < m; ++j) {
                System.out.printf("%-5d", board[i][j] == Integer.MAX_VALUE - 10 ? -1 : board[i][j]);
            }
            System.out.println();
        }
    }
    static boolean check(int x, int y, int n, int m){
        return x >= 0 && x <= n - 1 && y >= 0 && y <= m - 1;
    }
}

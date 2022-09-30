package lanqiao;

import java.util.Scanner;
import java.util.Stack;

public class C4 {
    static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static char[] chs = {'D', 'U', 'L', 'R'};
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        int idx = 0;
        boolean vis [][] = new boolean[30][50];
        int maze[][] = new int [30][50];
        for(int i = 0; i < 30; ++i) {
            for(int j = 0; j < 50; ++j) {
                maze[i][j] = str.charAt(idx++) - '0';
            }
        }
        Stack<Character> memo = new Stack<>();
        vis[0][0] = true;
        dfs(maze, 0, 0, vis, new Stack<Character>());
    }
    //dfs
    static void dfs(int[][]maze, int row, int col, boolean[][] vis, Stack<Character> memo){
        if(row == 29 && col == 40){
            Stack<Character> print = new Stack<>();
            while(!memo.isEmpty()){
                char ch = memo.pop();
                System.out.print(ch);
                print.push(ch);
            }
            while(!print.isEmpty()){
                memo.push(print.pop());
            }
        }
        for(int i = 0; i < direction.length; ++i) {
            int newRow = row + direction[i][0];
            int newCol = col + direction[i][1];
            if(check(newRow, newCol) && !vis[newRow][newCol] && maze[newRow][newCol] != 1){
                memo.push(chs[i]);
                vis[row + direction[i][0]][col + direction[i][1]] = true;
                dfs(maze, row + direction[i][0], col + direction[i][1], vis, memo);
                memo.pop();
                vis[row + direction[i][0]][col + direction[i][1]] = false;
            }
        }
    }
    static boolean check(int row, int col){
        return row >= 0 && row < 30 && col >= 0 && col < 50;
    }
}

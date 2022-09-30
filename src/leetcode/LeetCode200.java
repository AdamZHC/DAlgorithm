package leetcode;

public class LeetCode200 {
    public static void main(String[] args){
        char[][]grid = {
                {'0','1','0'},
                {'1','0','1'},
                {'0','1','0'}
        };
        new Solution109().numIslands(grid);
    }
}
class Solution109 {
    public int numIslands(char[][] grid) {
        //迷宫问题,但并非是回溯问题
        int cnt = 0;
        boolean[][] isVisited = new boolean [grid.length][grid[0].length];
        for(int i = 0; i < grid.length ; i ++){
            //两次循环自动换0，不需要取余
            for(int j = 0; j < grid[0].length ; j ++){
                if(isVisited[i][j] == false && grid[i][j] != '0'){
                    isVisited[i][j] = true;
                    forward(grid, i, j, isVisited);
                    cnt ++;
                }
            }
        }
        return cnt;
    }
    //开始递归
    void forward(char[][] grid, int row, int col,boolean[][] isVisited){
        if(row + 1  < grid.length && grid[row + 1][col] != '0' && isVisited[row + 1][col] == false){
            isVisited[row + 1][col] = true;
            forward(grid, row + 1 , col , isVisited);
        }
        if(row - 1  > -1 && grid[row - 1][col] != '0' && isVisited[row - 1][col] == false){
            isVisited[row - 1][col] = true;
            forward(grid, row - 1 , col , isVisited);
        }
        if(col + 1  < grid[0].length && grid[row][col + 1] != '0' && isVisited[row][col + 1] == false){
            isVisited[row][col + 1] = true;
            forward(grid, row , col + 1, isVisited);
        }
        if(col - 1  > -1 && grid[row][col - 1] != '0' && isVisited[row][col - 1] == false){
            isVisited[row][col - 1] = true;
            forward(grid, row, col - 1 , isVisited);
        }

    }
}
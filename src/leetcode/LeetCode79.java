package leetcode;

public class LeetCode79 {
    public static void main(String[] args){
        char[][] board = {
                {'a','a'}
        };
        String word = "aaa";
        System.out.println(new Solution79().exist(board,word));

    }
}
class Solution79 {
    public boolean exist(char[][] board, String word) {
        //简单的回溯思想
        //初始化是否访问的数组
        boolean [][] isVisited = new boolean[board.length][board[0].length];
        //进行深度优先搜索
        for(int i = 0; i < board.length ;i ++){
            for( int j = 0; j < board[0].length;j ++){
                isVisited[i][j] = true;
                if(dfs(board, isVisited, i, j, word, 0)) return true;
                isVisited[i][j] = false;
            }
        }
        //System.out.println(dfs(board, isVisited, 0, 0, word, 0));
        return false;
    }
    //思路：
    //1.应当有一个指针指向当前要匹配的字符 idx
    //2.应当传入是否访问过的布尔二维数组
    //3.有判断先后的顺序---也即上下左右
    //4.判断当前的二维指针指向哪一个位置row,col
    static boolean dfs(char[][] board, boolean[][]isVisited, int row, int col, String word, int idx){
        //这个表示之前已经匹配过了，否则没办法使用往后移动的深搜调用规则
        //其实就是一个思想怎么实现都可以
        //之前的棋盘小岛问题，就是用下一个，这回可以用这一个
        //而且还有一个关系就是在于for的回溯条件的调用，当然肯定是在深搜外部调用
        // 开始正式深搜 边界条件
        if(idx == word.length() - 1 && word.charAt(idx) == board[row][col] ) return true;
        if(word.charAt(idx) != board[row][col]) return false;
        if(row + 1 < board.length && !isVisited[row + 1][col]){
            isVisited[row + 1][col] = true;
            if( dfs(board, isVisited, row + 1, col, word, idx + 1)) return true;
            isVisited[row + 1][col] = false;
        }
        if(col + 1 < board[0].length && isVisited[row][col + 1] == false){
            isVisited[row][col + 1] = true;
            if( dfs(board, isVisited, row , col + 1, word, idx + 1)) return true;
            isVisited[row][col + 1] = false;
        }
        if(row - 1 > -1 && isVisited[row - 1][col] == false){
            isVisited[row - 1][col] = true;
            if( dfs(board, isVisited, row - 1, col, word, idx + 1)) return true;
            isVisited[row - 1][col] = false;
        }
        if(col - 1 > -1 && isVisited[row][col - 1] == false){
            isVisited[row][col - 1] = true;
            if( dfs(board, isVisited, row, col - 1, word, idx + 1)) return true;
            isVisited[row][col - 1] = false;
        }
        return false;
    }
}
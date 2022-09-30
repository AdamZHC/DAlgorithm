package codes.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode212 {
    public static void main(String[] args){
        char[][]board = {
                {'a','b','c'},
                {'a','e','d'},
                {'a','f','g'},
        };
        String[] word = new String[1];
        word[0] = "eaabcdgfa";
        new Solution212().findWords(board,word);
    }
}
class Solution212 {
    public List<String> findWords(char[][] board, String[] word) {
        boolean [][] isVisited = new boolean[board.length][board[0].length];
        List<String> ans = new ArrayList<String>();
        for(int k = 0 ; k < word.length ; k ++){
            //if(board.length * board[0].length < word[k].length()) return false;
            for(int i = 0; i < board.length; i ++){
                for(int j = 0; j < board[0].length; j ++){
                    isVisited[i][j] = true;
                    if(dfs(board, isVisited, i, j, word[k], 0) && !ans.contains(word[k])){
                        ans.add(word[k]);
                    }
                    isVisited[i][j] = false;
                }
            }
        }
        return ans;
    }
    static boolean dfs(char[][] board, boolean[][]isVisited, int row, int col, String word, int idx){
        if(idx == word.length() - 1 && word.charAt(idx) == board[row][col]) return true;
        if(word.charAt(idx) != board[row][col]) return false;
        if(row + 1 < board.length && isVisited[row + 1][col] == false){
            isVisited[row + 1][col] = true;
            if(dfs(board, isVisited, row + 1, col, word, idx + 1)) return true;
            isVisited[row + 1][col] = false;
        }
        if(col + 1 < board[0].length && isVisited[row][col + 1] == false){
            isVisited[row][col + 1] = true;
            if(dfs(board, isVisited, row, col + 1, word, idx + 1)) return true;
            isVisited[row][col + 1] = false;
        }
        if(row - 1 > -1 && isVisited[row - 1][col] == false){
            isVisited[row - 1][col] = true;
            if(dfs(board, isVisited, row - 1, col, word, idx + 1)) return true;
            isVisited[row - 1][col] = false;
        }
        if(col - 1 > -1 && isVisited[row][col - 1] == false){
            isVisited[row][col - 1] = true;
            if(dfs(board, isVisited, row, col - 1, word, idx + 1)) return true;
            isVisited[row][col - 1] = false;
        }
        return false;
    }
}
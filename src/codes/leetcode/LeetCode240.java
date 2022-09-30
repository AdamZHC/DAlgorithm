package codes.leetcode;

public class LeetCode240 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4, 4},
                {2, 5, 8, 12, 6},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        new Solution240().searchMatrix(matrix,7);
    }
}
class Solution240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        //思路很简单，也就是二分
        //找到最小值小于target的，最大值大于target的行
        //然后对这些列进行二分
        //二分寻找左侧的满足条件的列（最小值） ---> 返回值应当是一个 >= 0 的整数
        //首先排除特殊情况
        //如果左侧第一个就大于target直接返回false
        int row = matrix.length;
        int col = matrix[0].length;
        int scaleLeft = 0;
        if(matrix[0][0] == target) return true;
        if(matrix[0][0] > target) return false;
        //如果左侧最后一个也小于target的话，那就直接返回这次部分的结果
        if(matrix[row - 1][0] < target) scaleLeft = row - 1;
        else if(matrix[row - 1][0] == target) return true;
        else {
            int left = 0;
            int right = row - 1;
            while(left < right) {
                int mid = left + (right - left + 1) / 2;
                if(matrix[mid][0] == target) return true;
                if(matrix[mid][0] < target && matrix[mid + 1][0] > target){
                    scaleLeft = mid;
                    break;
                }else if(matrix[mid][0] > target){
                    right = mid - 1;
                }else{
                    left = mid;
                }
            }
        }
        //二分寻找右侧的满足条件的列（最小值） ---> 返回值应当是一个 >= 0 的整数
        int scaleRight = 0;
        if(matrix[row - 1][col - 1] == target) return true;
        if(matrix[row - 1][col - 1] < target) return false;
        //如果右侧第一个也大于target的话，那就直接返回这次部分的结果
        if(matrix[0][col - 1] > target) scaleRight = 0;
        else if(matrix[0][col - 1] == target) return true;
        else {
            int left = 0;
            int right = row - 1;
            while(left < right) {
                int mid = right - (right - left + 1) / 2;
                if(matrix[mid][col - 1] == target) return true;
                if(matrix[mid][col - 1] > target && matrix[mid - 1][col - 1] < target){
                    scaleRight = mid;
                    break;
                }else if(matrix[mid][col - 1] > target){
                    right = mid;
                }else{
                    left = mid + 1;
                }
            }
        }
        return scaleLeft == scaleRight;
    }
}
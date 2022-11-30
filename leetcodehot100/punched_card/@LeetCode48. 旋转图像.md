# @`LeetCode`48. 旋转图像

```java
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int i = 0; i < n; ++i) 
            for(int j = i; j < n; ++j) 
                swap1(matrix, i, j);
        for(int i = 0; i < n; ++i) 
            for(int j = 0; j < n / 2; ++j) 
                swap2(matrix, i, j, n);
    }
    void swap1(int[][] matrix, int i, int j) {
        if(matrix[i][j] == matrix[j][i])
            return;
        matrix[i][j] = matrix[i][j] ^ matrix[j][i];
        matrix[j][i] = matrix[i][j] ^ matrix[j][i];
        matrix[i][j] = matrix[i][j] ^ matrix[j][i];
    }
    void swap2(int[][] matrix, int i, int j, int n) {
        if(matrix[i][j] == matrix[i][n - j - 1])
            return;
        matrix[i][j] = matrix[i][j] ^ matrix[i][n - j - 1];
        matrix[i][n - j - 1] = matrix[i][j] ^ matrix[i][n - j - 1];
        matrix[i][j] = matrix[i][j] ^ matrix[i][n - j - 1];
    }

}
```


package lanqiao;

import java.util.Scanner;
// 1:无需package
// 2: 类名必须Main, 不可修改
import java.util.Arrays;
public class P505 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int l = scan.nextInt();
        int[][] triangle = new int [l][];
        for(int i = 1; i <= l; ++i) {
            triangle[i - 1] = new int[i];
            for(int j = 0; j < i; ++j) {
                triangle[i - 1][j] = scan.nextInt();
            }
        }
        scan.close();
        int cnt = 0;
        for(int i = 1; i < l; ++i) {
            for(int j = 0; j < i + 1; ++j) {
                if(j == 0){
                    triangle[i][j] += triangle[i - 1][j];
                }else if(j == i){
                    triangle[i][j] += triangle[i - 1][j - 1];
                }else{
                    triangle[i][j] += Math.max(triangle[i - 1][j], triangle[i - 1][j - 1]);
                }
            }
        }
        int ans = 0;
        if(l == 1) System.out.println(triangle[0][0]);
        else {
            if ((l & 1) == 1) {
                ans = Math.max(triangle[l - 1][(l - 1) / 2], triangle[l - 1][(l + 1) / 2]);
                ans = Math.max(ans, triangle[l - 1][l - 3]);
            } else {
                ans = Math.max(triangle[l - 1][(l - 2) / 2], triangle[l - 1][l / 2]);
            }
            System.out.println(ans);
        }
    }
}
package leetcode;

import java.util.Scanner;

public class Problem {
    public static void main(String[] args){
        while(true){
            Scanner sc = new Scanner(System.in);
            int a = sc.nextInt();
            System.out.println(result(a));
        }
    }
    public static int result(int a){
        if(a == 1){
            return a;
        }
        return a+result(a-1);
    }
}

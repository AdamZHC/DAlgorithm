package luogu;

import java.util.Scanner;

public class P1036 {
    static int ans = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] nums = new int[n];
        boolean[] vis = new boolean[n];
        for(int i = 0; i < n; ++i) {
            nums[i] = sc.nextInt();
        }
        dfs(k ,0, nums, vis, 0, 0);
        System.out.print(ans);

    }
    static void dfs(int k, int step, int[] nums, boolean[] vis, int num, int idx) {
        if(step == k){
            if(isPrime(num)){
                ans++;
            }
        }
        for(int i = idx; i < nums.length; ++i) {
            if(vis[i] == false){
                vis[i] = true;
                dfs(k, step + 1, nums, vis, num + nums[i], i);
                vis[i] = false;
            }
        }
    }
    static  boolean isPrime(int num) {
        for(int i = 2; i < Math.sqrt(num); ++i) {
            if(num % i == 0) return false;
        }
        return true;
    }
}

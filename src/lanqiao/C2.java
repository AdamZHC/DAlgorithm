package lanqiao;

public class C2 {
    public static void main(String[] args) {
        int dp[] = new int[20190324 + 1];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        for(int i = 4; i <= 20190324; ++i) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 10000;
        }
        System.out.println(dp[20190324]);
    }
}

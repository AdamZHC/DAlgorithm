package leetcode;

public class LeetCode319 {
    public static void main(String[] args) {
//        for(int i = 1; i < 68; ++i) {
//            solution(i);
//        }
        print(30);
    }
    static void solution(int n) {
        int [] nums = new int[n + 1];
        for(int i = 1; i <= n ; ++i) {
            for(int j = i; j <= n; j += i) {
                nums[j]++;
            }
        }
        int ans = 0;
        for(int i : nums) {
            ans += (i & 1) == 1 ? 0 : 1;
        }
        System.out.println(n - ans);
    }
    static void print(int n) {
        int num = 3;
        for(int i = 5; i < n; i += 2) {
            System.out.println(num);
            num += i;
        }
        for(int i = 1; i < n; ++i) {
            System.out.println(i * i - i + 3);
        }
    }
}

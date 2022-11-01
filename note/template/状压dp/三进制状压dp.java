package template.状压dp;//三进制状压dp
import java.util.*;

class Main {
	static Scanner in = new Scanner(System.in);
	final static int MOD = (int)1e6;
	static int n, m, k, ks;
	static int[] c3;
	static List<Integer> state = new ArrayList<>();
	static List<Integer>[] trans;
	static int[][] dp;
	public static void main(String[] args) {
		read();
		c3 = new int[m + 1];
		c3[0] = 1;
		for(int i = 1; i <= m; ++i)
			c3[i] = c3[i - 1] * 3;

		for(int i = 0; i < c3[m]; ++i)
			if(check1(i))
				state.add(i);

		int s = state.size();
		trans = new List[s + 1];
		dp = new int[n + 3][s];

		//因为存的是下标
		for(int i = 0; i < s; ++i) {
			trans[i] = new ArrayList<>();
			for(int j = 0; j < s; ++j) {
				int st1 = state.get(i), st2 = state.get(j);
				if(check2(st1, st2))
					trans[i].add(j);
			}
		}
		int f = state.indexOf(ks);
		if(f == -1) {
			System.out.println(0);
			return;
		}
		dp[0][0] = 1;
		for(int i = 1; i <= k - 1; ++i) 
			for(int j = 0; j < s; ++j)
				for(int k : trans[j]) 
					dp[i][j] = (dp[i][j] + dp[i - 1][k]) % MOD;
				
			
		
		dp[k][f] = 1;
		int ans1 = 0;
		for(int i : trans[k]) {
			dp[k + 1][i] = (dp[k + 1][i] + dp[k][f]) % MOD;
			ans1 = (ans1 + dp[k - 1][i]) % MOD;
		}

		for(int i = k + 2; i <= n; ++i)
			for(int j = 0; j < s; ++j)
				for(int k : trans[j])
					dp[i][j] = (dp[i][j] + dp[i - 1][k]) % MOD;

		int ans2 = 0;
		for(int i = 0; i < s; ++i)
			ans2 = (ans2 + dp[n][i]) % MOD;

		System.out.println(((long)ans1 * (long)ans2) % MOD);

	}
	//取出x的第u位的状态(从0开始)
	static int get(int x, int u) {
		return x % c3[u] / c3[u + 1];
	}
	static boolean check1(int st) {
		for(int i = 0; i < m - 1; ++i) 
			if(get(st, i) == get(st, i + 1))
				return false;
		return true;
	}
	static boolean check2(int st1, int st2) {
		for(int i = 0; i < m; ++i) 
			if(get(st1, i) == get(st2, i))
				return false;
		return true;
		
	}
	static void read() {
		n = in.nextInt(); m = in.nextInt(); k = in.nextInt();
		for(int i = 0; i < 3; ++i) {
			int u = in.nextInt();
			ks = ks * 3 + u - 1;
		}
	}
}
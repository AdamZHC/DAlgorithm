package template.树形dp;

import java.util.*;

class Main {
	static Scanner in = new Scanner(System.in);
	static int n;
	static T t;
	static int[] din;
	static int[][] dp;
	public static void main(String[] args) {

		int root = read();

		dfs(root);

		System.out.println(Math.min(dp[root][1], dp[root][0]));
	}

	static void dfs(int root) {
		for(int i = t.hd[root]; i != -1; i = t.nex[i]) {
			int to = t.to[i];
			//后序dfs
			dfs(to);
			//更新dp
			dp[root][0] += dp[to][1];
			dp[root][1] += Math.min(dp[to][1], dp[to][0]);
		}
		
	}

	static int read() {
		n = in.nextInt();
		din = new int[n + 1];
		dp = new int[n + 1][2];
		//初始化dp
		for(int i = 1; i <= n; ++i)
			for(int j = 0; j < 2; ++j)
				dp[i][j] = 0x7fffffff;

		for(int i = 1; i <= n; ++i) {
			int u = in.nextInt(), w = in.nextInt(), cc = in.nextInt();
			dp[u][1] = w;
			for(int j = 0; j < cc; ++j) {
				int v = in.nextInt();
				din[v]++;
				g.add(u, v);
			}
		}		

		for(int i = 1; i <= n; ++i)
			if(din[i] == 0)
				return i;
	}
}

class T {
	int[] to, nex, hd;
	int idx;
	public T (int m, int n) {
		to = new int[m]; nex = new int[m];
		hd = new int[n + 1];
		Arrays.fill(hd, -1);
	}
	public void add(int u, int v) {
		to[idx] = v;
		nex[idx] = hd[u];
		hd[u] = idx++;
	}
}
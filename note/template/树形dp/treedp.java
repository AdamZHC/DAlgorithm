package template.树形dp;

import java.util.*;

class Main {
	static Scanner in = new Scanner(System.in);
	static int n, m;
	static int[] wgt;
	static int[][] dp;
	static G g;
	public static void main(String[] args) {
		read();

		dfs(0);

		System.out.println(dp[0][m + 1]);
	}
	static void dfs(int root) {
		//其实对于树形dp有类似的模型套路
		//对于没有上司的舞会是一种思想是在遍历中实现dfs
		//对于选课也是类似的是在遍历中实现dfs
		//遍历物品
		for(int i = g.hd[root]; i != -1; i = g.nex[i]) {
			int to = g.to[i];
			dfs(to);
			//分组背包
			//01或者分组背包的统一处理思路，从大到小遍历
			for(int j = m + 1; j >= 1; --j) 
				//选择第k个物品等价于dp[to][k]
				for(int k = 1; k <= m + 1; ++k)
					if(j > k)
						dp[root][j] = Math.max(dp[root][j], dp[root][j - k] + dp[to][k]);
			
		}
	}
	static void read() {
		n = in.nextInt();
		m = in.nextInt();
		//那个根节点必须要选
		dp = new int[n + 1][m + 2];
		wgt = new int[n + 1];
		g = new G(n, n);
		for(int i = 1; i <= n; ++i) {
			int ki = in.nextInt();
			int si = in.nextInt();
			wgt[i] = si;
			g.add(ki, i);
			//这里的初始化，在dp中是允许的
			//也就是说这里实现了部分的更新 只要在dp中满足，更新是满足的！！！
			//在dp中不更新的是时候是一样的
			dp[i][1] = si;
		}
	}
}
class G {
	int[] to, nex, hd;
	int idx;
	public G (int m, int n) {
		nex = new int[m]; to = new int[m];
		hd = new int[n + 1];
		Arrays.fill(hd, -1);
	}
	public void add(int u, int v) {
		to[idx] = v;
		nex[idx] = hd[u];
		hd[u] = idx++;
	}
}
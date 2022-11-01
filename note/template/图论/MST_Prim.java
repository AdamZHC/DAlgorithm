package template.图论;

import java.util.*;
class Main {
	public static void main(String[] args) {
		//处理读入
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		//这里的图应该就是直接取存就行
		//因为最小生成树的思想就是不需要特别地处理这一部分
		int[][] mat = new int[n + 1][n + 1];
		for(int i = 0; i < m; ++i) {
			int u = in.nextInt();
			int v = in.nextInt();
			int w = in.nextInt();
			//因为是最小，所以需要取最小
			mat[u][v] = Math.min(mat[u][v], w);
			mat[v][u] = Math.min(mat[v][u], w);
		}

		//初始化关于最小生成树的相关变量
		int[] lc = new int[n + 1];
		boolean[] vis = new boolean[n + 1];
		Arrays.fill(lc, 0x7fffffff);
		lc[1] = 0;
		int ans = 0;
		boolean flag = true;
		for(int i = 1; i <= n; ++i) {
			//选出最小的那个
			int t = -1;
			for(int j = 1; j <= n; ++j) 
				if(!vis[j] && (t == -1 || lc[j] < lc[t]))
					t = j;
			if(lc[t] == 0x7fffffff) {
				flag = false;
				break;
			}
			ans += lc[t];
			vis[t] = true;
			//更新lc数组
			for(int k = 1; k <= n; ++k) 
				//不等于0说明有边
				if(mat[k][t] != 0 && !vis[k]) 
					lc[k] = Math.min(lc[k], mat[k][t]);
		}
		System.out.println(flag ? ans : "impossible");
	}
}
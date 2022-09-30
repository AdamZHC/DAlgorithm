package template.树形dp;

import java.util.*;

class Main {
	static Scanner in = new Scanner(System.in);
	static int n;
	static T t;
	//dp相关的
	int[] d1, d2, p, up;
	public static void main(String[] args) {
		read();

		dfs1(1, -1);

		dfs2(1, -1);

		int ans = 0;
		for(int i = 1; i <= n; ++i)
			ans = Math.max(d1[i], up[i]);

		System.out.println(ans);

	}

	//处理下面的d1 d2 p
	static void dfs1(int root, int fa) {
		for(int i = t.hd[root]; i != -1; i = t.nex[i]) {
			int to = t.to[i], wgt = t.to[i];
			if(to == fa)
				continue;
			dfs1(to, root);
			//更换最大值
			if(d1[root] <= d1[to] + wgt) {
				d2[root] = d1[root];
				d1[root] = d1[to] + wgt;
				p[root] = to;
			} else if(d2[root] < d1[to] + wgt)
				d2[root] = d1[to] + wgt;
		}
	}

	static void dfs2(int root, int fa) {
		for(int i = t.hd[root]; i != -1; i = t.nex[i]) {
			int to = t.hd[root], wgt = t.to[i];
			if(to == fa)
				continue;
			if(p[root] == to)
				up[to] = Math.max(d2[root], up[root]) + wgt;
			else
				up[to] = Math.max(d1[root], up[root]) + wgt;
			dfs2(to, root) 
		}
	}

	static void read() {
		n = in.nextInt();
		d1 = new int[n + 1];
		d2 = new int[n + 1];
		up = new int[n + 1];
		p = new int[n + 1];
		t = new T(n << 1, n);
		for(int i = 0; i < n - 1; ++i) {
			int ui = in.nextInt(), vi = in.nextInt(), wi = in.nextInt();
			t.add(ui, vi, wi);
			t.add(vi, ui, wi);
		}
	}
}

class T {
	int[] to, nex, wgt, hd;
	int idx;
	public T (int m, int n) {
		to = new int[m]; nex = new int[m]; wgt = new int[m];
		hd = new int[n + 1];
		Arrays.fill(hd, -1);
	}
	public void add(int u, int v, int w) {
		to[idx] = v;
		wgt[idx] = w;
		nex[idx] = hd[u];
		hd[u] = idx++;
	} 
}
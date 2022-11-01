package template.图论;

import java.util.*;
//拓扑排序不能处理没有边的图
//也就是说必须得是连通图
class Main {
	static Scanner in = new Scanner(System.in);
	static L l;
	static int n, h;
	static int[] din;
	public static void main(String[] args) {
		read();

		int[] arr = topSort();

		System.out.println(Arrays.toString(arr));
	}
	static int[] topSort() {
		int[] arr = new int[n];
		int cnt = 0;

		Queue<Integer> q = new ArrayDeque<>();
		for(int i = 1; i <= n; ++i)
			if(din[i] == 0)
				q.offer(i);

		while(!q.isEmpty()) {
			int pl = q.poll();

			for(int i = l.hd[pl]; i != -1; i = l.nex[i]) {
				int to = l.to[i];
				din[to]--;
				if(din[to] == 0) 
					q.offer(to);
			}
		}

		return arr;
	}

	static void read() {
		n = in.nextInt(); h = in.nextInt();
		l = new L (n, n - 1);
		din = new int[n + 1];
		for(int i = 1; i < n; ++i) {
			int x = in.nextInt(), y = in.nextInt(), z = in.nextInt();
			if(z == 0) {
				l.add(x, y);
				din[y]++;
			} else {
				l.add(y, x);
				din[x]++;
			} 
		}
	}
}
//链式前向星
class L {
	int[] nex, to, hd;
	int idx;

	public L (int n, int m) {
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
//package template.图论;//LCA
//import java.util.*;
////此时考虑的话，就不需要加上对应的限制了，因为也不用离散化
//class Main {
//	final static int INF = 0x7fffffff;
//	public static void main(String[] args) {
//		//处理读入和建树
//		Scanner in = new Scanner(System.in);
//		int n =  in.nextInt();
//		int m = in.nextInt();
//		int root = in.nextInt();
//		//因为序号是固定的所以不需要在外面定义final变量了
//		T t = new T(n, n - 1);
//		for(int i = 0; i < n - 1; ++i) {
//			int u = in.nextInt(), v = in.nextInt();
//			t.add(u, v); t.add(v, u);
//		}
//
//		//初始化和维护fa数组和depth
//		int[][] fa = new int[n + 1][20];
//		//突然想到最短路也可以把dis数组放到外面去处理！！这都是很灵活的，当然太裸的话那就不用管了
//		int[] depth = new int[n + 1];
//
//		bfs(fa, depth, t, 1);
//
//		//to redouble algorithm LCA
//		for(int i = 0; i < m; ++i) {
//			int x = in.nextInt(), y = in.nextInt();
//			int anc = LCA(fa, depth, x, y);
//			System.out.println(anc);
//		}
//	}
//	//return lca
//	static int LCA(int[][] fa, int[] depth, int x, int y) {
//		//binary scrabble
//		if(depth[x] < depth[y]) {
//			x = x ^ y; y = x ^ y; x = x ^ y;
//		}
//
//		//jump to the same layer
//		for(int k = 19; k >= 0; --k)
//			if(depth[fa[x][k]] >= depth[y])
//				x = fa[x][k];
//		if(x == y)
//			return x;
//		for(int k = 19; k >= 0; --k)
//			if(fa[x][k] != fa[y][k]) {
//				x = fa[x][k];
//				y = fa[y][k];
//			}
//		return fa[x][0];
//	}
//
//	static void bfs(int[][] fa, int[] depth, T t, int n, int root) {
//		//主要是进行一个广搜的处理
//		boolean[] vis = new boolean[n + 1];
//		Queue<Integer> q = new ArrayDeque<>();
//
//		//初始化——哨兵等
//		Arrays.fill(depth, INF);
//		depth[0] = 0; depth[root] = 1;
//		vis[root] = true;
//		q.offer(root);
//
//		while(!q.isEmpty()) {
//			int pl = q.poll();
//			for(int i = t.hd[pl]; i != -1; i = t.nex[i]) {
//				int to = t.to[i];
//				if(!vis[to]) {
//					vis[to] = true;
//					q.offer(to);
//					depth[to] = depth[pl] + 1;
//
//					//update fa array
//					fa[to][0] = pl;
//					for(int j = 1; j <= 19; ++j) {
//						fa[to][j] = fa[fa[to][j - 1]][j - 1];
//					}
//				}
//			}
//		}
//
//	}
//}
////链式前向星
//class T {
//	int[] to;
//	int[] nex;
//	int[] hd;
//	int idx;
//
//	public T (int n ,int m) {
//		to = new int[m];
//		nex = new int[m];
//		hd = new int[n + 1];
//		Arrays.fill(hd, -1);
//	}
//
//	public void add(int u, int v) {
//		to[idx] = v;
//		nex[idx] = hd[u];
//		hd[u] = idx++;
//	}
//}
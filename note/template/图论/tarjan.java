package template.图论;

import java.util.*;
//Tarjan算法
class Main {
	//灵活一点，把全局要用到的就放到外面就行，static G , UF
	//为了方便耦合！！！
	//这里养成一个习惯，尤其是图论的题，把一些统一的变量声明在外面
	//比如说图G,并差集UF，还有点数边数，Scanner 放到外面
	//另外呢，把这些读入封装成一个函数
    static 	Map<Integer, List<int[]>> query = new HashMap<>();
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		T t = new T(n, n * 2);
		for(int i = 0; i < n - 1; ++i) {
			int u = in.nextInt();
			int v = in.nextInt();
			int w = in.nextInt();
			t.add(u, v, w);
			t.add(v, u, w);
		}
		//存对应的询问
		// int[][] query = new int[m][2]; 
		for(int i = 0; i < m; ++i) {
			int u = in.nextInt();
			int v = in.nextInt();
			if(u != v) {
			    if(query.get(u) == null) {
    				List<int[]> e = new ArrayList<>();
    				e.add(new int[]{v, i});
    				query.put(u, e);
    			} else {
    				query.get(u).add(new int[]{v, i});
    			}
    
    			if(query.get(v) == null) {
    				List<int[]> e = new ArrayList<>();
    				e.add(new int[]{u, i});
    				query.put(v, e);
    			} else {
    				query.get(v).add(new int[]{u, i});
    			}
			}
		}

		//对应的初始化离根节点的距离
		int[] dis = new int[n + 1];

		dfs(dis, t, 1, -1);

		UF uf = new UF(n);
		int[] res = new int[m];
		int[] vis = new int[n + 1];
		tarjan(t, uf, dis, res, 1, vis);
		
		for(int i = 0; i < m; ++i)
		    System.out.println(res[i]);
	}
	//说实话，不太能懂这个是啥意思
	static void dfs(int[] dis, T t, int s, int f) {
		for(int i = t.hd[s]; i != -1; i = t.nex[i]) {
			int to = t.to[i];
			int w = t.wgt[i];
			if(to == f) 
				continue;
			dis[to] = dis[s] + w;
			dfs(dis, t, to, s); 
		}
	}

	static void tarjan(T t, UF uf, int[] dis, int[] res, int u, int[] vis) { 
		vis[u] = 1;
		for(int i = t.hd[u]; i != -1; i = t.nex[i]) {
			int to = t.to[i];
			//没有访问过才要访问
			if(vis[to] == 0) {
				tarjan(t, uf, dis, res, to, vis);
				uf.unite(u, to);
			}
		}
        if(query.containsKey(u)) {
            for(int[] q : query.get(u)) {
    			int y = q[0];
    			int id = q[1];
    			if(vis[y] == 2) {
    				int anc = uf.findSet(y);
    				res[id] = dis[y] + dis[u] - 2 * dis[anc];
    			}
    		}
        }
		vis[u] = 2;
	}
}

class UF {
	int[] parent;
	int[] size;

	public UF(int n) {
		parent = new int[n + 1];
		size = new int[n + 1];
		Arrays.fill(size, 1);
		for(int i = 1; i <= n; ++i)
			parent[i] = i;
	}

	public int findSet(int x) {
		return x == parent[x] ? x : (parent[x] = findSet(parent[x]));
	}

	public boolean unite(int x, int y) {
		x = findSet(x);
		y = findSet(y);
// 		if(x == y)
// 			return false;
// 		if(size[x] < size[y]) {
// 			x = x ^ y;
// 			y = x ^ y;
// 			x = x ^ y;
// 		}

		parent[y] = x;
// 		size[x] += size[y];
		return true;
	}

	public boolean connected(int x, int y) {
		return findSet(x) == findSet(y);
	}
}
//链式前向星
class T {
	int[]to;
	int[]wgt;
	int[] nex;
	int[] hd;
	int idx;

	public T (int n ,int m) {
		to = new int[m]; 
		wgt = new int[m]; 
		nex = new int[m];
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
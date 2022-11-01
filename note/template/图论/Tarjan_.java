package template.图论;

import java.util.Arrays;
import java.util.Vector;

class Tarjan {
	static G g;
	public static void main(String[] args) {
		//seq
		Vector<Integer> l = new Vector<>();
		boolean[] vis = new boolean[10];
		
		dfs(1, vis, l);
	} 

	//欧拉路径的处理
	static void dfs(int u, boolean[] vis, Vector<Integer> v) {

		for(int i = g.hd[u]; i != -1; i = g.nex[i]) {
			int to = g.to[i], w = g.wgt[i];
			if(!vis[to]) {
				vis[to] = true;
				dfs(i, vis, v);
			}	
		}

		v.add(u);
	}
	static void read() {
		//建图
	}
}

class G2 {
	int[] to, nex, wgt, hd;
	int idx;

	public G2(int n, int m) {
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
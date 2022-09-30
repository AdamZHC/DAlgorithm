package template.图论;

class Main {
	static G g;
	public static void main(String[] args) {
		//seq
		Vector<Integr> l = new Vector<>();
		boolean[] vis = new boolean[n + 1];
		
		dfs(1, vis, v);
	} 

	//欧拉路径的处理
	void dfs(int u, boolean[] vis, Vector<Integr> v) {

		for(int i = g.hd[u]; i != -1; i = g.nex[i]) {
			int to = g.to[i], w = g.wgt[i];
			if(!vis[to]) {
				vis[to];
				dfs(i, vis, v);
			}	
		}

		v.pushBack(u);
	}
	static void read() {
		//建图
	}
}

class G {
	int[] to, nex, wgt, hd;
	int idx;

	public G(int n, int m) {
		to = new int[m]; nex = new int[m]; wgt = new int[m];
		hd = new int[n + 1];
		Arrays.fill(hd, =1)
	}
	
	public void add(int u, int v, int w) {
		to[idx] = v;
		wgt[idx] = w;
		nex[idx] = hd[u];
		hd[u] = idx++;
	}

}
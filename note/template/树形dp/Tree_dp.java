package template.树形dp;//二叉苹果树
//其实就是可以选择不留下结点
//选择留下边 其实是可以实现的 没事慢慢来
//其实也就是留下q + 1
//树的中心就是可以直接来处理！！！最小高度树也就是半径
//因为那种需要父节点的情况总可以被其它情况(父节点的子树)来包括，所以并不需要
//维护父节点的，只需要维护每个结点的任意子节点就行
import java.util.*;

class Main {
	static Scanner in = new Scanner(System.in);
	static int n, q;
	static G g;
	static int[][] dp;
	public static void main(String[] args) {

		read();

		dfs(1, -1);

		System.out.println(dp[n][q]);

	}
	static void dfs(int root, int fa) {
		//遍历物品
		for(int i = g.hd[root]; i != -1; i = g.nex[i]) {
			int to = g.to[i], wgt = g.wgt[i];
			if(to == fa)
				continue;
			dfs(to, root);
			//遍历容量
			for(int j = q; j >= 1; --j) 
				for(int k = 0; k < j; ++k)
					//这里的j - k - 1就是很离谱需要考虑从之前转移过来
					dp[root][j] = Math.max(dp[root][j], dp[root][j - k - 1] + dp[to][k] + wgt);
		}
	}
	static void read() {
		n = in.nextInt();
		q = in.nextInt();
		g = new G(n * 2, n);
		dp = new int[n + 1][q + 1];
		for(int i = 0; i < n - 1; ++i) {
			int u = in.nextInt(), v = in.nextInt(), w = in.nextInt();
			g.add(u, v, w);
			g.add(v, u, w);
		}
	}

}
class G {
	int[] to, nex, hd, wgt;
	int idx;
	public G (int m, int n) {
		to = new int[m]; nex = new int[m];
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
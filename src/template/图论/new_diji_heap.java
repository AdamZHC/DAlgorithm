package template.图论;

import java.util.*;
class Main {
	static Scanner in = new Scanner(System.in);
	static int n, m, INF = 0x7fffffff;
	static G gz, gf;
	public static void main(String[] args) {

		read();

		int[] disz = dij(gz);
		int[] disf = dij(gf);

		int ans = 0;

		for(int i = 1; i <= n; ++i) {
			ans += disz[i];
			ans += disf[i];
		}

		System.out.println(ans);
	} 

	static int[] dij(G g) {
		int[] dis = new int[n + 1];
		boolean[] vis = new boolean[n + 1];
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

		Arrays.fill(dis, INF);
		dis[1] = 0;
		pq.offer(new int[]{1, dis[1]});

		while(!pq.isEmpty()) {

			while(!pq.isEmpty() && vis[pq.peek()[0]])
				pq.poll();
			if(pq.isEmpty())
				break;
			int[] pl = pq.poll();
			vis[pl[0]] = true;

			for(int i = g.hd[pl[0]]; i != -1; i = g.nex[i]) {
				int to = g.to[i];
				int w = g.wgt[i];
				if(dis[to] > dis[pl[0]] + w) {
					dis[to] = dis[pl[0]] + w;
					pq.offer(new int[]{to, dis[to]});
				}
			}
		}

		return dis;
	}

	static void read() {
		n = in.nextInt();
		m = in.nextInt();
		g = new G(n, m);
		for(int i = 0; i < m; ++i) {
			int u = in.nextInt(), v = in.nextInt(), w = in.nextInt(); 
			gz.add(u, v, w);
			gf.add(v, u, w);
		}
	}
}
class G {
	int[] to, nex, wgt, hd;
	int idx;

	public G (int n, int m) {
		to = new int[m];
		nex = new int[m];
		wgt = new int[m];
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
package template.图论;

import java.util.*;
class Main {
	public static void main(String[] args) {
		//spfa算法模板
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		//建图完成
		G g = new G(m, n);
		for(int i = 0; i < m; ++i) {
			int u = in.nextInt();
			int v = in.nextInt();
			int w = in.nextInt();
			g.add(u, v, w);
		}
		//初始化对应的队列等等
		//标记是否在队列中
		boolean vis[] = new boolean[n + 1];
		vis[1] = true; 
		int[] dis = new int[n + 1];
		Arrays.fill(dis, 0x7fffffff);
		dis[1] = 0;
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(1);
		while(!q.isEmpty()) {
			int pl = q.poll();
			vis[pl] = false;
			//开始松弛操作
			for(int i = g.hd[pl]; i != -1; i = g.nex[i]) {
				//对应需要更新的边就是顶点编号to[i]的边
				if(dis[pl] + g.wgt[i] < dis[g.to[i]]) {
					//这里有点不同的思想——松弛完有可能不入队
					//如果访问过的话就不入队了
					dis[g.to[i]] = dis[pl] + g.wgt[i];
					//注意入队前要进行刷新操作
					if(!vis[g.to[i]]) {
						vis[g.to[i]] = true;
						q.offer(g.to[i]);
					}
				}
			}
		}
		System.out.println(dis[n] == 0x7fffffff ? "impossible" : dis[n]);
	}
}
//图论算法当然需要链式前向星
class G {
	int[] to;
	int[] nex;
	int[] wgt;
	int[] hd;
	int idx;
	public G(int m, int n) {
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
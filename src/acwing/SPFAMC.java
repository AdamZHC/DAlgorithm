package acwing;

import java.util.*;

//spfa求负环
public class SPFAMC {
    public static void main(String[] args) {
        //处理读入
        Scanner in = new Scanner(System.in);
//        int t = in.nextInt();
//        for(int p = 0; p < t; ++p) {
            int n = in.nextInt();
            int m = in.nextInt();
            G g = new G(n,  m * 2);
            for(int i = 0; i < m; ++i) {
                int u = in.nextInt();
                int v = in.nextInt();
                int w = in.nextInt();
                if(w >= 0) {
                    g.add(u, v, w);
                    g.add(v, u, w);
                } else {
                    g.add(u, v, w);
                }
            }
            //spfa
            int[] cnt = new int[n + 1];
            int[] dis = new int[n + 1];
            boolean[] vis = new boolean[n + 1];
            Arrays.fill(dis, 0x7fffffff);
            dis[1] = 0;
            vis[1] = true;
            Queue<Integer> q = new ArrayDeque<>();
            q.offer(1);
            cnt[1]++;
            boolean ans = true;
            while(!q.isEmpty()) {
                int pl = q.poll();
                vis[pl] = false;
                for(int i = g.hd[pl]; i != -1; i = g.nex[i]) {
                    int to = g.to[i];
                    int w = g.wgt[i];
                    if(dis[to] > dis[pl] + w) {
                        dis[to] = dis[pl] + w;
                        if(!vis[to]) {
                            vis[to] = true;
                            cnt[to] = cnt[pl] + 1;
                            q.offer(to);
                            if(cnt[to] > n)  {
                                System.out.println(cnt[to]);
                                ans = true;
                                break;
                            }
                        }
                    }
                }
                if(ans)
                    break;
            }
            System.out.println(Arrays.toString(cnt));
            System.out.println(ans ? "YES" : "NO");
//        }
    }
}
//链式前向星
class G {
    int[] to;
    int[] wgt;
    int[] nex;
    int[] hd;
    int idx;
    public G(int n, int m) {
        to = new int[m];
        wgt = new int[m];
        nex =new int[m];
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
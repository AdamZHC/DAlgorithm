package luogu;

//Prim算法
import java.util.*;
public class P3366 {
    public static void main(String[] args) {
        //Prim算法

        //处理读入问题
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        G g = new G(m * 2, n);
        for(int i = 0; i < m; ++i) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            g.add(u, v, w);
            g.add(v, u, w);
        }

        //开始正式地Prim算法 + 堆优化
        //离他最远地距离
        int[] lc = new int[n + 1];
        //st表示state
        boolean[] vis = new boolean[n + 1];
        //初始化对应的优先队列
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int ans = 0;
        Arrays.fill(lc, 0x7fffffff);
        //因为不会存在自环，所以说这里没有问题
        lc[4] = 0;
        pq.offer(new int[]{4, lc[4]});
        while(!pq.isEmpty()) {
            //加入堆优化的这里不能忘掉啊
            while(!pq.isEmpty() && vis[pq.peek()[0]])
                pq.poll();
            if(pq.isEmpty())
                break;
            int[] pl = pq.remove();
            System.out.println();
            vis[pl[0]] = true;
            ans += pl[1];
            //处理对应的权重注意!!!不需要记录从哪一点转移过来的，然后因为对应的最小的
            //就已经代表此时的最小转移过来的值，然后不需要记录其它的，最好把这里清空

            for(int i = g.hd[pl[0]]; i != -1; i = g.nex[i]) {
                //i表示边的编号 to表示点的编号
                int to = g.to[i];
                int w = g.wgt[i];
                if(w < lc[to] && !vis[to]) {
                    lc[to] = w;
                    pq.offer(new int[]{to, lc[to]});

                }
            }
        }
        //输出答案
        boolean flag = true;
        for(int i = 1; i <= n; ++i)
            if(lc[i] == 0x7fffffff) {
                flag = false;
                break;
            }
// 		System.out.println(ans);
 		System.out.println(Arrays.toString(lc));
        //对于负环的情况就需要这样来处理一下
        System.out.println(flag ? ans : "impossible");
    }
}
//链式前向星
class G {
    int[] to;
    int[] nex;
    int[] wgt;
    int[] hd;
    int idx;
    public G (int m, int n) {
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

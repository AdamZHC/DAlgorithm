# @`LeetCode`207. 课程表

```java
class Solution {
    G g;
    int[] din;
    public boolean canFinish(int n, int[][] edges) {
        int m = edges.length;
        din = new int[n];
        g = new G(m, n);
        for(int[] e : edges) {
            g.add(e[1], e[0]);
            din[e[0]]++;
        }
        return bfs();
    }
    boolean bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < din.length; ++i)
            if(din[i] == 0)
                q.offer(i);
        while(!q.isEmpty()) {
            int p = q.poll();
            for(int i = g.hd[p]; i != -1; i = g.nex[i]) {
                int to = g.to[i];
                din[to]--;
                if(din[to] == 0)
                    q.offer(to);
            }
        }
        for(int i : din)
            if(i != 0)
                return false;
        return true;
    }

}
class G {
    int[] to, nex, hd;
    int idx;
    public G (int m, int n) {
        to = new int[m]; nex = new int[m]; 
        hd = new int[n];
        Arrays.fill(hd, -1);
    }
    public void add(int u, int v) {
        to[idx] = v;
        nex[idx] = hd[u];
        hd[u] = idx++;
    }
}
```


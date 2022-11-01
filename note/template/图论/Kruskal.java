package template.图论;

import java.util.*;
class Main {
    public static void main(String[] args) {
        //处理读入和建图
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        Edge[] edges = new Edge[m];
        for(int i = 0; i < m; ++i)  {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            edges[i] = new Edge(u, v, w);
        }
        //Kruskal
        //排序
        Arrays.sort(edges);
        UF uf = new UF(n);

        //维护并查集和标志最小生成树是否形成的Int变量
        int ll = 1;
        int idx = 0;	
        int ans = 0;
        //一旦等于n说明此时他就是形成最小生成树了
        while(ll < n && idx < n) {
        	int u = edges[idx].u;
        	int v = edges[idx].v;
        	int w = edges[idx].w;
        	if(!uf.connected(u, v)) {
        		ans += w;
        		ll++;
        		uf.unite(u, v);
        	}
        	idx++;
        }
        System.out.println(ll == n ? ans : "impossible");
    }
}
//对边排序的话就得这样
class Edge implements Comparable {
    int u, v, w;
    public Edge(int u, int v, int w) {
        this.u = u; this.v = v; this.w = w;
    }

    //注意这里是Object
    @Override
    public int compareTo(Object o) {
        Edge e = (Edge)o;
        return w - e.w;
    }
}
class UF {
    int[] parent;
    int[] size;
    int setCount;

    public UF(int n) {
        parent = new int[n + 1];
        size = new int[n + 1];
        Arrays.fill(size, 1);
        for(int i = 1; i <= n; ++i)
            parent[i] = i;
    }

    public int findSet(int x) {
        return parent[x] == x ? x : (parent[x] = findSet(parent[x]));
    }

    public boolean unite(int x, int y) {
        x = findSet(x);
        y = findSet(y);
        if(x == y)
            return false;
        if(size[x] < size[y]) {
            x = x ^ y;
            y = x ^ y;
            x = x ^ y;
        }
        parent[y] = x;
        size[x] += size[y];
        --setCount;
        return true;
    }

    public boolean connected(int x, int y) {
        return findSet(x) == findSet(y);
    }
}
//并差集的实现也可以是基于图，就是说，枚举所以的情况之后，看两个情况是否有连接
//就是通过最先开始的建图实现的，也就是比较方便，如果两个点被选中且是连接的，那就连接他们即可
//然后并差集的连接也就是只需要基于原来的图就可以 经典的边抽象
//判断连通块的个数可以通过parent[i] == i来而且有条件的会更好
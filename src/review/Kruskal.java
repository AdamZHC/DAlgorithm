package review;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Kruskal {
    //考虑的是带权无向图，因为是最小生成树，没有路径的概念
    //只有是否连通的概念
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        //图是从标号为1的点开始的
        //直接就为边集
        int[][] edges = new int[m][3];
        for(int i = 0; i < m; ++i) {
            edges[i][0] = in.nextInt();
            edges[i][1] = in.nextInt();
            edges[i][2] = in.nextInt();
        }
        //稍复杂的实现就加上{}
        //lambda表达式就是直接(o1, o2) -> o2[2] - o1[2]
        //用闭包就是(o1, o2) -> {return o2[2] - o1[2];}
        //再复杂一点就是 new Comparator<int[]>(){
        //  @Override
        //  public int compare(int[]o1, int[]o2) {
        //      return o1[2] - o2[2];
        //  }
        //}
        Arrays.sort(edges, (o1, o2) -> o1[2] - o2[2]);
        //排序
        //此时就是不把0算进去
        //有可能会出现编号为0的情况，所以为了耦合性以及一致性（一般+1的问题都是在外面处理，并非在模板内处理）
        //把+1放到外面就可以
        //不需要借助HashSet() 直接用并查集 然后判断连同分量即可
        UnionFind u = new UnionFind(n + 1);
        int ans = 0;
        for(int i = 0; i < m && u.conn > 2; ++i) {
            int v1 = edges[i][0];
            int v2 = edges[i][1];
            if(!u.isConnected(v1, v2)){
                ans += edges[i][2];
                u.unite(v1, v2);
            }
        }
        System.out.println(ans == 0 ? "orz" : ans);
    }
}
//并查集
//这里也不一定是顶点，有可能是其它的边或者类似于岛屿数量那样的题
//所以这里要注意就用element
class UnionFind {
    private int[] parent;
    private int[] weight;
    int size;//代表并查集中元素个数
    int conn;//代表并查集中有多少个连通分量

    public UnionFind(int size) {
        this.parent = new int[size];
        this.weight = new int[size];
        this.size = size;
        this.conn = size;//因为初始的时候每个人自成一组，所以有多少人就有多少组
        for (int i = 0; i < size; i++) {
            this.parent[i] = i;
            this.weight[i] = 1;
        }
    }

    public int find(int e) {
        while (e != parent[e]) {
            parent[e] = parent[parent[e]];
            e = parent[e];
        }
        return e;
    }

    public boolean isConnected(int e1, int e2) {
        return find(e1) == find(e2);
    }

    public void unite(int e1, int e2) {
        int r1 = find(e1);
        int r2 = find(e2);

        //如果已经属于同一个集合了，就不用再合并了。
        if (r1 == r2) {
            return;
        }

        if (weight[r1] > weight[r2]) {
            parent[r2] = r1;
            weight[r1] += weight[r2];
        } else {//weight[r1] <= weight[r2]
            parent[r1] = r2;
            weight[r2] += weight[r1];
        }

        //合并 e1 和 e2 所在的两个组后，就少了一组。
        this.conn--;
    }
}
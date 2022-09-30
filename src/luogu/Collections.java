package luogu;


import java.util.*;

public class Collections {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] query = new int[m][3];
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < 3; ++j) {
                query[i][j] = in.nextInt();
            }
        }
        int ans = 0;
        UnionFind u = new UnionFind(n);
        for(int[] q : query) {
            if(q[0] == 1){
                u.unite(q[1], q[2]);
            }else{
                System.out.println(u.connected(q[1], q[2]) ? "Y" : "N");
            }
        }
        System.out.println(ans);
    }
}
class UnionFind {
    int[] parent;
    int[] size;
    int n;
    // 当前连通分量数目
    int setCount;

    public UnionFind(int n) {
        this.n = n;
        this.setCount = n;
        this.parent = new int[n];
        this.size = new int[n];
        Arrays.fill(size, 1);
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
        }
    }

    public int findset(int x) {
        return parent[x] == x ? x : (parent[x] = findset(parent[x]));
    }

    public boolean unite(int x, int y) {
        x = findset(x);
        y = findset(y);
        if (x == y) {
            return false;
        }
        if (size[x] < size[y]) {
            int temp = x;
            x = y;
            y = temp;
        }
        parent[y] = x;
        size[x] += size[y];
        --setCount;
        return true;
    }

    public boolean connected(int x, int y) {
        x = findset(x);
        y = findset(y);
        return x == y;
    }
}

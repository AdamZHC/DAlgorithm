# @树形dp
### 树的直径 树的中心
常规处理就是对于每个结点的下面最长的距离和次长距离，还有对上个结点的处理
```java
import java.util.*;

class Main {
    static Scanner in = new Scanner(System.in);
    //只考虑树的直径的情况下只需要维护一个变量
    //而且后面的情况会把之前的情况考虑到
    static int n, ans; 
    static T t;
    
    public static void main(String[] args) {
        
        read();
        
        dfs(1, -1);
        
        System.out.println(ans);
    }
    
    static int dfs(int root, int fa) {
        int dis = 0;
        
        int d1 = 0, d2 = 0;
        for(int i = t.hd[root]; i != -1; i = t.nex[i]) {
            int to = t.to[i], w = t.wgt[i];
            if(to == fa)
                continue;
            int d = dfs(to, root) + w;
            dis = Math.max(dis, d);
            
            if(d >= d1) {
                d2 = d1; 
                d1 = d;
            } else if(d > d2) 
                d2 = d;
        }
        
        ans = Math.max(ans, d1 + d2);
        return dis;
    }
    static void read() {
        n = in.nextInt();
        t = new T(n, (n - 1) * 2);
        for(int i = 1; i < n; ++i) {
            int u = in.nextInt(), v = in.nextInt(), w = in.nextInt();
            t.add(u, v, w);
            t.add(v, u, w);
        }
    }
}
class T {
    int[] to, nex, hd, wgt;
    int idx;
    
    public T (int n, int m) {
        to = new int[m]; nex = new int[m]; wgt = new int[m];
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
```

```java
import java.util.*;

class Main {
	static Scanner in = new Scanner(System.in);
	static int n;
	static T t;
	//dp相关的
    //如果是需要找到具体的点等等情况 则需要维护很多dp相关的变量
	static int[] d1, d2, p, up;
	public static void main(String[] args) {
		read();

		dfs1(1, -1);

		dfs2(1, -1);

		int ans = 0x7fffffff;
		
		for(int i = 1; i <= n; ++i)
			ans = Math.min(ans, Math.max(d1[i], up[i]));

		System.out.println(ans);

	}

	//处理下面的d1 d2 p
	static void dfs1(int root, int fa) {
		for(int i = t.hd[root]; i != -1; i = t.nex[i]) {
			int to = t.to[i], wgt = t.wgt[i];
			if(to == fa)
				continue;
			dfs1(to, root);
			//更换最大值
			if(d1[root] <= d1[to] + wgt) {
				d2[root] = d1[root];
				d1[root] = d1[to] + wgt;
				p[root] = to;
			} else if(d2[root] < d1[to] + wgt)
				d2[root] = d1[to] + wgt;
		}
	}

	static void dfs2(int root, int fa) {
		for(int i = t.hd[root]; i != -1; i = t.nex[i]) {
			int to = t.to[i], wgt = t.wgt[i];
			if(to == fa)
				continue;
			if(p[root] == to)
				up[to] = Math.max(d2[root], up[root]) + wgt;
			else
				up[to] = Math.max(d1[root], up[root]) + wgt;
			dfs2(to, root);
		}
	}

	static void read() {
		n = in.nextInt();
		d1 = new int[n + 1];
		d2 = new int[n + 1];
		up = new int[n + 1];
		p = new int[n + 1];
		t = new T(n << 1, n);
		for(int i = 0; i < n - 1; ++i) {
			int ui = in.nextInt(), vi = in.nextInt(), wi = in.nextInt();
			t.add(ui, vi, wi);
			t.add(vi, ui, wi);
		}
	}
}

class T {
	int[] to, nex, wgt, hd;
	int idx;
	public T (int m, int n) {
		to = new int[m]; nex = new int[m]; wgt = new int[m];
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
```
### 树形背包 树形状态机(见没有上司的舞会)

```java
//二叉苹果树
//其实就是可以选择不留下结点
//选择留下边 其实是可以实现的 没事慢慢来
//其实也就是留下q + 1
import java.util.*;

class Main {
	static Scanner in = new Scanner(System.in);
	static int n, q;
	static G g;
	static int[][] dp;
	public static void main(String[] args) {

		read();

		dfs(1, -1);

		System.out.println(dp[1][q]);

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
		to = new int[m]; nex = new int[m]; wgt = new int[m];
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
```
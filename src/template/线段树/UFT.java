package template.线段树;

//并查集模板
//默写一下
class UnionFind {
    //关键是这里的parent数组
    int[] parent;
    //通过这个来优化时间复杂度
    //根据某个集合的点数来实现
    int[] size;
    //最初始的连通分量数
    //这也是构造方法需要传入的参数
    //setCount的维护比较自由，所以说也不需要怎么改进
    int setCount;

    //对应的构造方法
    public UnionFind (int n) {
        //最初始的连通分量数
        //当前的连通分量数也是这样的
        this.setCount = n;
        this.parent = new int[n + 1];
        this.size = new int[n + 1];
        //当前的大小都是1
        Arrays.fill(size, 1);
        //对应的父节点都是自己本身
        for(int i = 1; i <= n; ++i) 
            parent[i] = i;
    }

    //确定属于哪一个连通分量
    //这里的操作十分牛逼——这是因为可以递归的方法
    //以及语法的二义性来实现对应的让对应的根节点的
    //直接子节点尽可能地多，从而实现降低时间复杂度
    public int findset(int x) {
        return parent[x] == x ? x : (parent[x] = findset(parent[x]));
    }

    //连通操作
    //这里返回是否成功
    public boolean unite(int x, int y) {
        //寻找对应的连通分量的根结点
        //实现对应的合并操作
        x = findset(x);
        y = findset(y);
        //如果是属于同一连通分量的话那就不能连通
        if(x == y) {
            return false;
        }
        //成功的合并操作——只需要对应的比较连通分量的个数从而实现
        if(size[x] < size[y]) {
            //交换操作
            x = x ^ y;
            y = x ^ y;
            x = x ^ y;
        }
        parent[y] = x;
        size[x] += size[y];
        --setCount;
        return true;
    }

    //判断是否为同一连通分量
    public boolean connected(int x, int y) {
        x = findset(x);
        y = findset(y);
        return x == y;
    }
}
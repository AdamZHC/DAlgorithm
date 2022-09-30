package codes.scanline;


import java.util.Scanner;

public class SegmentTree {
    Scanner in = new Scanner(System.in);
    Node[] tree;
    public SegmentTree(int[] arr) {
        String s = "asdasd";

        int n = arr.length;
        tree = new Node[n << 2];
        createTree(arr, 0, n - 1, 0);
    }
    //优先队列可以直接移除对应的
    //有可能存了好几个对应的边，记录加进去三个点，碰到两个需要删除的，那就根据哈希表删两个
    //不能只删去一个，也不能都删掉，所以这就是为啥使用哈希表去存储
    /**
     *
     * @param arr:对应的数组
     * @param L:该结点的数组左边界对应下标
     * @param R:该结点的数组右边界对应下标
     * @param idx:线段树结点的位置
     */
    //按照算法竞赛的思路来不会出错，就像之前的字典树一样，自己写就容易出错，人家写的有人家的道理
    //通过下标去索引对应的坐标
    //Node(L, R, data)
    //树是通过顺序存储来保存，就是(0,0)是对应的根节点
    //就按照人家的竞赛模板来就可以
    public void createTree(int[] arr, int L, int R, int idx){
        if(L == R){
            tree[idx] = new Node(L, R, arr[L], 0);
            return;
        }
        //M也指的是对应的下标
        int M = (L + R) / 2;
        createTree(arr, L, M, idx * 2 + 1);
        createTree(arr, M + 1, R, idx * 2 + 2);
        tree[idx] = new Node(L, R, tree[idx * 2 + 1].data + tree[idx * 2 + 2].data, 0);
    }
    //区间修改——对于实际数组的区间(L, R)的参数传递是不变的
    //参数不变的好处就是可以持续比较对应的(L, R),另外只需要比较M = tree[idx].L + (tree[idx].R - tree[idx].L - 1) / 2
    //和对应的(L, R)的关系就可以
    public void update(int L, int R, int idx, int d){
        if(tree[idx].L >= L && tree[idx].R <= R){
            tree[idx].data += (tree[idx].R -tree[idx].L + 1) * d;
            tree[idx].lazyTag += d;
            return;
        }
        //只要是访问到这里就一定需要pushDown
        pushDown(idx);
        int M = M(idx);
        if(M >= L)
            update(L, R, idx * 2 + 1, d);
        if(M < R)
            update(L, R, idx * 2 + 2, d);
        tree[idx].data = tree[idx * 2 + 1].data + tree[idx * 2 + 2].data;
    }

    public void update(int I, int idx, int d){
        if(I == idx){
            tree[idx].data += d;
            return;
        }
        pushDown(idx);
        //注意中间的这个是在前面有的，所以就是前面需要I <= M
        int M = M(idx);
        if(I <= M)
            update(I, idx * 2 + 1, d);
        else
            update(I, idx * 2 + 2, d);

        tree[idx].data = tree[idx * 2 + 1].data + tree[idx * 2 + 2].data;
    }
    public int query(int I, int idx){
        if(I == idx){
            return tree[idx].data;
        }
        pushDown(idx);
        int M = M(idx);
        if(M <= I)
            return query(I, idx * 2 + 1);
        else
            return query(I, idx *  2 + 2);
    }
    public int query(int L, int R, int idx){
        if(tree[idx].L >= L && tree[idx].R <= R){
            return tree[idx].data;
        }
        pushDown(idx);
        int M = M(idx);
        return (M >= L ? query(L, R, idx * 2 + 1) : 0) + (M < R ? query(L, R, idx * 2 + 2) : 0);
    }

    public int M(int idx){
        return (tree[idx].R + tree[idx].L) / 2;
    }

    //其实只要访问到了对应的结点就应该下放懒标了
    public void pushDown(int idx) {
        if(tree[idx].lazyTag != 0){
            Node leftNode = tree[idx * 2 + 1];
            leftNode.lazyTag = tree[idx].lazyTag;
            leftNode.data += (leftNode.R - leftNode.L + 1) * tree[idx].lazyTag;
            Node rightNode = tree[idx * 2 + 2];
            rightNode.lazyTag = tree[idx].lazyTag;
            rightNode.data += (rightNode.R - rightNode.L + 1) * tree[idx].lazyTag;
            tree[idx].lazyTag = 0;
        }
    }
    public void detail() {
        for(Node node : tree){
            if(node == null){
                System.out.println();
            }else{
                node.detail();
            }
        }

    }
}

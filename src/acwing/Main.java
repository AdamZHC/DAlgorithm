package acwing;

import java.util.*;

class Main {
    static Scanner in = new Scanner(System.in);
    static int p = 0, la = 0, ll = 0;
    static Sgt sgt;
    public static void main(String[] args) {
        int m = in.nextInt();
        p = in.nextInt();
        in.nextLine();
        sgt = new Sgt(m);
        while(m-- != 0)
            read();
        Set<Integer>[] sets = new Set[4];
        sets[1] = new HashSet<>();

    }
    static void read() {
        String line = in.nextLine();
        char ch = line.charAt(0);
        int v = Integer.parseInt(line.substring(2));
        if(ch == 'A') {
            sgt.modify(1, ll + 1, (v + la) % p);
            ll++;
        } else {
            la = sgt.query(1, ll - v + 1, ll);
            System.out.println(la);
        }
    }
}

class Nd {
    int l, r, v;
}
class Sgt {
    Nd[] tr;
    int[] arr;
    int n;
    //这个长度其实很自由的
    //但是我就规定长度就是最后一个可访问的下标的大小
    //因为需要build 其它操作不影响
    public Sgt(int n) {
        this.n = n;
        arr = new int[n + 1];
        Arrays.fill(arr, 0);
        tr = new Nd[n << 2];
        for(int i = 1; i < n << 2; ++i)
            tr[i] = new Nd();
        build(1, 1, n);
    }
    public void build(int u, int l, int r) {
        tr[u].l = l;
        tr[u].r = r;
        if(l == r) {
            tr[u].v = arr[l];
            return;
        }
        int mid = l + r >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }
    public void pushup(int u) {
        tr[u].v = Math.max(tr[u << 1].v, tr[u << 1 | 1].v);
    }
    public int query(int u, int l, int r) {
        if(tr[u].l >= l && tr[u].r <= r)
            return tr[u].v;
        int res = 0;
        int mid = tr[u].l + tr[u].r >> 1;

        if(l <= mid)
            res = Math.max(res, query(u << 1, l, r));
        if(r >= mid + 1)
            res = Math.max(res, query(u << 1 | 1, l, r));

        return res;
    }
    public void modify(int u, int x, int v) {
        if(tr[u].l == x && tr[u].r == x)
            tr[u].v = v;
        else {
            int mid = tr[u].l + tr[u].r >> 1;
            if(x <= mid)
                modify(u << 1, x, v);
            else
                modify(u << 1 | 1, x, v);
            pushup(u);
        }
    }
}


package template.线段树;

class Nd {
	int l, r, v;
}
class Sgt {
	Nd[] tr;
	int n;
	int[] arr;
	public Sgt(int[] arr, int n) {
		//传入参数
		this.n = n; this.arr = arr;
		//初始化树
		tr = new Nd[n << 2];
		for(Nd e : tr)
			e = new Nd();
		build(1, 1, n);
	}
	public void build(int u, int l, int r) {
		if(l == r) {
			tr[i].v = arr[u];
			return;
		}
		int mid = l + r >> 1;
		build(u << 1, l, mid);
		build(u << 1 | 1, mid + 1, r);
		pushup(u);
	}
	public void pushup(int u) {
		tr[u].v = tr[u << 1].v + tr[u << 1 | 1].v;
	}
	public int query(int u, int l, int r) {
		if(tr[u].l >= l && tr[u].r <= r)
			return tr[u].v;
		int sum = 0;
		int mid = tr[u].l + tr[u].r >> 1;
		if(l <= mid)
			sum += query(u << 1, l, r);
		//不重不漏
		if(r > mid)
			sum += query(u << 1 | 1, l, r);
		return sum;
	}
	public void modify(int u, int t, int v) {
		if(tr[u].l == tr[u].r) {
			tr[u].v = v;
			return;
		}
		int mid = tr[u].l + tr[u].r >> 1;
		if(t <= mid)
			modify(u << 1, t, v);
		else 
			modify(u << 1 | 1, t, v);
		pushup(u);
	}
}
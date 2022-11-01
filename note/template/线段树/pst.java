package template.线段树;

class Nd {
	int l, r;
	int sum, add;
}
class Sgt {
	Nd[] tr;
	int[] arr;
	int n;
	public Sgt (int[] arr, int n) {
		this.arr = arr; this.n = n;
		tr = new Nd[n << 2];
		for(int i = 1; i < n << 2; ++i)
			tr[i] = new Nd();
		build(1, 1, n);
	}
	public void build(int u, int l, int r) {
		tr[u].l = l; tr[u].r = r;
		if(l == r) {
			tr[u].sum = arr[l];
			tr[u].add = 0;
			return;
		}
		int mid = l + r >> 1;
		build(u << 1, l, mid);
		build(u << 1 | 1, mid + 1, r);
		pushup(u);
	}
	//这里不涉及到懒标记的事情
	public void pushup(int u) {
		tr[u].sum = tr[u << 1].sum + tr[u << 1 | 1].sum;
	}
	public void modify(int u, int l, int r, int d) {
		if(l <= tr[u].l && r >= tr[u].r) {
			tr[u].sum += (tr[u].r - tr[u].l + 1) * d;
			tr[u].add += d;
			return;
		}
		pushdown(u);
		int mid = tr[u].l + tr[u].r >> 1;
		if(l <= mid)
			modify(u << 1, l, r, d);
		if(r >= mid + 1)
			modify(u << 1 | 1, l, r, d);
		pushup(u);
	}
	public int query(int u, int l, int r) {
		if(l <= tr[u].l && r >= tr[u].r) 
			return tr[u].sum;
		pushdown(u);
		int mid = tr[u].l + tr[u].r >> 1;
		int res = 0;
		if(l <= mid)
			res += query(u << 1, l, r);
		if(r >= mid + 1)
			res += query(u << 1 | 1, l, r);
		return res;
	}
	//因为是完全覆盖，所以说加和就是本身区间长度
	public void pushdown(int u) {
		Nd root = tr[u]; Nd ls = tr[u << 1]; Nd rs = tr[u << 1 | 1];
		ls.sum += (ls.r - ls.l + 1) * root.add; ls.add += root.add;
		rs.sum += (rs.r - rs.l + 1) * root.add; rs.add += root.add;
		root.add = 0;
	}
}
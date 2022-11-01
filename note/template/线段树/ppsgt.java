package template.线段树;

class Nd {
	int l, r;
	int sum, add, mul;
}
class Sgt {
	Nd[] tr;
	int[] arr;
	int n;
	public Sgt(int[] arr, int n) {
		this.arr = arr; this.n = n;
		tr = new Nd[n << 2];
		for(int i = 1; i < n << 2; ++i) {
			tr[i] = new Nd();
			//这里注意需要进行初始化为1的操作
			tr[i].mul = 1;
		}
		build(1, 1, n);
	}
	public void build(int u, int l, int r) {
		tr[u].l = l; tr[u].r = r;
		if(l == r) {
			tr[u].sum = arr[i];
			return;
		}
		int mid = l + r >> 1;
		build(u << 1, l, mid);
		build(u << 1 | 1, mid + 1, r);
		pushup(u);
	}
	public void pushup(int u) {
		tr[u].sum = tr[u << 1].sum + tr[u << 1 | 1].sum;
	} 
	public void pushdown (int u){
		Nd ls = tr[u << 1]; Nd rs = tr[u << 1 | 1];
		eval(ls, tr[u].add, tr[u].mul); eval(rs, tr[u].add, tr[u].mul);
		tr[u].add = 0; tr[u].mul = 1;
	}
	public void eval(Nd root, int add, int mul) {
		root.sum = root.sum * mul + (root.r - root.l + 1) * add;
		root.mul = root.mul * mul;
		root.add = root.add * mul + add;
	}
	public void modify(int u, int l, int r, int add, int mul) {
		if(l <= tr[u].l && r >= tr[u].r) {
			eval(tr[u], add, mul);
			return;
		}
		pushdown(u);
		int mid = tr[u].l + tr[u].r >> 1;
		if(l <= mid)
			modify(u << 1, l, r, add, mul);
		if(r >= mid + 1)
			modify(u << 1 | 1, l, r, add, mul);
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
			res += query(u < 1 | 1, l, r);
		return res;
	}
}

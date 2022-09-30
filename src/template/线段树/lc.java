package template.线段树;

class Nd {
	int l, r;
	int ls, rs;
	int val;
}
class Sgt {
	Nd[] tr;
	int idx;
	public Sgt(int n) {
		idx++;
		tr = new Nd[n << 2];
		for(int i = 0; i < n << 2; ++i)
			tr[i] = new Nd();
		build(idx, 1, (int)1e5);
	}
	//只有build是l, r
	void build(int l, int r, int u) {
		tr[idx] = l; tr[idx] = r;
	}
	void pushup(int u) {
		tr[u].val = tr[tr[u].ls].val + tr[tr[u].rs].val;
	}
	void nmake(int u) {
		int mid = tr[u].l + tr[u].r >> 1;
		if(tr[u].ls == 0) {
			tr[u].ls = ++idx;
			tr[tr[u].ls].l = tr[u].l;
			tr[tr[u].ls].r = mid;
		}
		if(tr[u].rs == 0) {
			tr[u].rs = ++idx;
			tr[tr[u].rs].l = mid + 1;
			tr[tr[u].rs].r = tr[u].r;
		}
	}
	//其它都是对于u的左右结点
	//因为这是需要的，如果用下面的也可以
	//但是其实都是一样的
	void modify(int u, int x) {
		if(tr[u].l == tr[u].r) {
			tr[u].val++;
			return;
		}
		int mid = tr[u].l + tr[u].r >> 1;
		nmake(u);
		if(x <= mid)
			modify(tr[u].ls, x);
		else
			modify(tr[u].rs, x);
		pushup(u);
	}
	int query(int u, int l, int r) {
		if(tr[u].l >= l && tr[u].r <= r) 
			return tr[u].val;
		int mid = tr[u].l + tr[u].r >> 1, res = 0;
		if(l <= mid)
			res += query(tr[u].ls, l, r);
		if(r >= mid + 1)
			res += query(tr[u].rs, l, r);
		return res;
	}
}
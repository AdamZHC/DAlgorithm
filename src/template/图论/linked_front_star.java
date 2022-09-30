package template.图论;

class Main {
	public static void main(String[] args)  {
		//因为在主类的内部定义链式前向星总需要static关键字，所以我们选择定义一个类
		//类似于并查集一样——每次需要的时候就直接写一下就行
		//尝试用链式前向星来存
	}
}
//这样的抽象比较好
class G {
	//某边的终点
	int[] to;
	//与该边相同的起点的上一条边
	int[] nex;
	//这条边的权重
	int[] wgt;
	//以k为起点的最后一条边的编号
	int[] hd;
	//控制边的输入
	int idx;
	//n个顶点，m条边
	public G(int n, int m) {
		to = new int[m];
		nex = new int[m];
		wgt = new int[m];
		hd = new int[n];
		Arrays.fill(hd, -1);
	}
	public void add(int u, int v, int w) {
		to[idx] = v;
		wgt[idx] = w;
		nex[idx] = hd[u];
		hd[u] = idx++;
	}
}
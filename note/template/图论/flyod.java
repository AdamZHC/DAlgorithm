package template.图论;

import java.util.Arrays;
import java.util.Scanner;

class flyod {
	//flyod
	//一般图的邻接矩阵的处理也是和这个是类似的
	//对于一般地，只需要把有边的矩阵分配边就可以了
	//然后初始化d[][], 需要填充0x7fffffff和0, 最终只借助于原来的边矩阵就可以了
	public static void main(String[] args)  {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int[][] dd = new int[n + 1][n + 1];
		for(int i = 1; i <= n; ++i)
			Arrays.fill(dd[i], 0x3f3f3f);
		//注意在本身这里需要处理一个读入
		//这回有经验了，floyd需要基本上需要再复制一遍邻接矩阵！！！
		//或者就在本身上操作——整体上这里的处理可能需要复杂一些的处理一下啊
		//i != j && 无边 -> INF  有边就是边长 i == j -> 0
		for(int k = 1; k <= n; ++k)
			for(int i = 1; i <= n; ++i)
				for(int j = 1; j <= n; ++j)
					dd[i][j] = Math.min(dd[i][j], dd[i][k] + dd[k][j]);
	}	
}
package basic.algorithm;
import java.util.*;

public class DijkstraDemo {
    /**
     * 注意用到的思想是广度优先搜索+贪心算法
     * dis数组动态保存结果，保存的就是离每个结点的最短距离
     * 记录已经访问过点的集合，还是遍历添加核心是三个数组
     * 1.前驱结点
     * 2.距离数组
     * 3.是否访问过
     * 这个懂了，看CSDN
     * 类似于广度优先搜索，其实用到它的思想但是，这里的层扩展访问都是
     * 放到一个优先队列（堆）里面实现的
     * */
    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();
        for(int a : q) {

        }
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt();
        //直接用邻接矩阵来存就好了
        int[][] matrix = new int[n + 1][n + 1];
        for(int i = 0; i < m; ++i) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            matrix[u][v] = w;
        }
        //matrix[i][j] == 0表示没有边
        //使用堆优化的最短路
        //注意就是把对应更新后的点往进扔就可以了
        //不需要关注对应的是否访问过
        //其实还是延迟删除的思想——就是堆的思路
        //dis表示距离目标点的最短距离
        //或者说是当前距离
        int[] dis = new int[n + 1];
        Arrays.fill(dis, 0x7fffffff);
        //这里是正确的还没有开始连接到其它的边上去
        dis[s] = 0;
        boolean[] u = new boolean[n + 1];
        //目标点已经确定了——注意是在出队的时候
        //判定是否更新答案
        //里面存的就是距离
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> dis[o1] - dis[o2]);
        pq.offer(s);
        while(!pq.isEmpty()) {
            //这里需要改一下
            //等待会儿再改
            while(pq.peek() != null && u[pq.peek()])
                pq.poll();
            //这里需要判断一下，并且需要特殊处理一下
            if(pq.isEmpty())
                break;
            int pl = pq.poll();
            u[pl] = true;
            //这样去更新里面的每一个相连接的点
            for(int i = 1; i <= n; ++i) {
                //说明有连接
                if(matrix[pl][i] != 0 && !u[i]) {
                    dis[i] = Math.min(dis[i], dis[pl] + matrix[pl][i]);
                    //更新答案的时候不需要阻止
                    pq.offer(i);
                }
            }
        }
        for(int i = 1; i <= n; ++i)
            System.out.printf("%d ", dis[i]);
    }
}

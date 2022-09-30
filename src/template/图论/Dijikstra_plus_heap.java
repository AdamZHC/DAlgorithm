package template.图论;

import java.util.*;
class Main {
    //Dijikstra的重边和自环都可以过，没有影响
    //不能处理负权
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt();
        //用链式前向星
        G g = new G(n, m);
        for(int i = 0; i < m; ++i) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            g.add(u, v, w);
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
        //这里应该是存两份，不应该只是一份
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
            //注意i是边的集合，表示以pl为起点的边的集合，to就是对应的边的终点
            for(int i = g.hd[pl]; i != -1; i = g.nex[i]) {
                //说明有连接
                if(!u[g.to[i]]) {
                    dis[g.to[i]] = Math.min(dis[g.to[i]], dis[pl] + g.wgt[i]);
                    //更新答案的时候不需要阻止
                    pq.offer(g.to[i]);
                }
            }
        }
        for(int i = 1; i <= n; ++i)
            System.out.printf("%d ", dis[i]);
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
        hd = new int[n + 1];
        Arrays.fill(hd, -1);
    }
    public void add(int u, int v, int w) {
        to[idx] = v;
        wgt[idx] = w;
        nex[idx] = hd[u];
        hd[u] = idx++;
    }
}
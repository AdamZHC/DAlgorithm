package codes.algorithm;

public class KruskalDemo {
    //Kruskal算法是从边的角度出发来生成最小生成树
    //不想那么多，图的问题都是拿邻接矩阵来处理的
    /**
     * 边的个数，邻接矩阵，顶点数组
     * 在图论的算法中都是要有的就是邻接矩阵，关键就是邻接矩阵，其它的根据实际情况来定
     * 因为这里以边为主要的考量对象，所以要用边的个数，还有顶点数组
     * kruskal需要边类！！！和我的思路是一样的
     * 先排序然后判断是否为回路，判断回路有一个地方要处理，就是终点问题，如果我自己想的话，肯定是想不出来的
     * 遍历边的数组，也就是对象数组，排序好的每一个边，然后获取到对应的
     * 索引，也是用两个变量p1,p2
     * 起点和终点也就是在输入的时候加入start end总有一个小的，小的那个就是start
     * 这个index就是保证一个一个加，就是之前cnt的小技巧
     */
    /**
    public void kruskal(){
        for(int i = 0 ; i<edgeNum; i++ ){ --在已排序数组中去寻找最的边，直到找满(结点个数-1)个边
            获取两条边，注意边类有起点和终点两个成员变量
            int p1 = getPosition(edges[i].start);(起点)
            int p2 = getPosition(edges[i].end);
            获取p1,p2两个顶点在已有的最小生成树中的终点
            int m = getEnd(ends,p1);
            int n = getEnd(ends,p2);
            是否构成回路
            if( m!=n ){
                ends[m] = n;
                rets[index++] = edges[i];
            }
        }
    }
    private int getEnd(int[] ends, int i){
        while(ends[i] != 0){
            i = ends[i]; -- end就是动态变化的那个数组每次保存每个结点对应的终点
        }
        return i;
    }
}**/
}


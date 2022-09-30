package basic.algorithm;

import basic.graph.Graph;

import java.util.HashSet;
import java.util.Set;
//思路就是创建一个最小生成树的类
class MinTree{
    /**
     * 这一步是构筑普利姆算法的精髓
     * 核心是用h1 h2记录两个结点，然后用布尔数组记录访问过的结点
     * 用二维数组记录边，每次要遍历一边这个邻接矩阵，根据是否访问过可以筛选出
     * 满足条件的边，最后就记录就可以了存入对应的顺序数组和布尔数组
     * Prim的实现Dijkstra的实现有点像，也就说每次加入新的结点和新的边都要去更新数组，lowCost[i]是距离当前最小生成树的边起点
     * 贪心的思路也是类似的
     * 老师的顺序数组没有，因为老师直接用System.out.println()来可视化显示，思路是一样的
     * @param graph//要生成最小生成树的那个图
     * @param v//从哪个顶点开始生成
     */
    public void prim(Graph graph, int v){
        //一个布尔数组表示是否访问过
        boolean isVisited[] = new boolean[graph.vertex];
        //先初始化当前结点表示访问过
        isVisited[v] = true;
        //用下面的变量记录两个下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;
        for(int k = 1; k < graph.vertex;k++){//产生n-1条边
            //这个思想也是通过邻接矩阵（二维数组）来遍历通过一个布尔数组来记录被访问过
            //最终把可以出现的最小的边来筛选出来，
            //注意观察if条件--前者i意思是在这个在准最小生成树的集合里面的结点，后者j的意思是
            //还没有访问过的结点，最后就是看他们直接相邻的边的最小的情况
            for(int i = 0; i< graph.vertex;i++){
                for (int j = 0 ;j<graph.vertex;j++){
                    if(isVisited[i] == true && isVisited[j] == false && PrimDemo.matrix[i][j] < minWeight){
                        minWeight = PrimDemo.matrix[i][j];
                        //最终赋值
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            isVisited[h2] = true;
            minWeight = 10000;
            //应该还有一条记录是用来记录增加的顺序
        }
    }
}
public class PrimDemo {
    //用二维数组去存 -- 需要学习韩老师的处理方法
    static int[][] matrix = new int[7][7];
    //初始化
    public static void initial(){
        matrix[0][1] = 5;matrix[0][2] = 7;matrix[0][6] = 2;//A
        matrix[1][0] = 5;matrix[1][6] = 3;matrix[1][3] = 9;//B
        matrix[2][0] = 7;matrix[2][4] = 5;//C
        matrix[3][1] = 9;matrix[3][5] = 4;//D
        matrix[4][2] = 8;matrix[4][6] = 4;matrix[4][5] = 5;//E
        matrix[5][3] = 4;matrix[5][6] = 6;matrix[5][4] = 5;//F
        matrix[6][4] = 4;matrix[6][5] = 8;matrix[6][0] = 2;matrix[6][1] = 3;//G
    }
    static boolean[] isVisited = new boolean[7];
    static boolean[] road = new boolean[7];
    public static void main(String[] args){
        Set<Integer> set = new HashSet<Integer>();
        set.add(0);
        while(set.size() != 7){
            prim(set);
        }
    }
    public static void prim(Set<Integer> set){
        int minOut = 0, minIn = 0;
        for(int i : set){
            for(int j = 0 ;j < matrix[i].length;j++){
                if(isVisited[j] == false){
                    minIn = matrix[i][j] < matrix[i][minIn] ? j : minIn;
                }
            }
            minOut = matrix[i][minIn] < matrix[minOut][minIn] ? i : minOut;
        }
        road[minOut] = true;
        isVisited[minIn] = true;
    }
}

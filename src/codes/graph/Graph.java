package codes.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//这次就试一下韩老师平时的那个思路，不是思想就是一般的处理方式
public class Graph {
    public static void main(String[] args){
        Graph graph = new Graph(4);
        graph.addVertex("北京");
        graph.addVertex("上海");
        graph.addVertex("广州");
        graph.addVertex("深圳");
        graph.addEdges("北京","成都",200);
//        codes.graph.addEdges("北京","上海",200);
//        codes.graph.addEdges("北京","广州",400);
//        codes.graph.addEdges("北京","深圳",250);
//        codes.graph.addEdges("上海","广州",100);
//        codes.graph.addEdges("上海","深圳",180);
//        codes.graph.addEdges("深圳","广州",200);
//        codes.graph.show();
    }
    //使用邻接矩阵表示图
    //需要图的顶点 -- 需要用容器（Collection）来表示顶点
    //其实个人感觉最好是用数组，因为这个数组有天然的对应关系，如果使用容器的话就需要再使用一个哈希表来维护，或者只有一个哈希表（也需要自己创建）
    //需要邻接矩阵表示边（根据这个特点就决定了某图的最大的容量就确定了）
    //无向图
    //以下为自己的思路：
    String[] vertexes;
    int[][] edges;
    public int vertex;// 表示顶点的个数
    //构造方法确定容量大小
    public Graph(int n){
        vertexes = new String[n];
        edges = new int[n][n];
    }
    //添加顶点（结点）
    public void addVertex(String vertex){
        if(vertexes[vertexes.length-1] != null){
            System.out.println("图容量已满");
            return;
        }
        //一直找到
        int i=0;
        while(vertexes[i]!=null){
            i++;
        }
        vertexes[i] = vertex;
    }
    //添加边
    public void addEdges(String v1, String v2, int weight){
        int flag1 = -1,flag2 = -1;
        int i = 0;
        while(true){
            if(flag1 == -1 && vertexes[i].equals(v1)){
                flag1 = i;
            }
            if(flag2 == -1 && vertexes[i].equals(v2)){
                flag2 = i;
            }
            //如果两个同时不等于零说明都找到了，跳出循环
            if(flag2 != -1 && flag1 != -1){
                break;
            }
            //如果能够到这一步，说明上面的判断没有通过，也就是没有同时为-1，说明有一个不存在
            if(i==vertexes.length-1){
                System.out.println("未找到对应的顶点");
                return;//没有找到说明没法添加边，这时就返回
            }
            i++;
        }
        edges[flag1][flag2] = weight;
        edges[flag2][flag1] = weight;
    }
    public String[] getVertex(){
        return vertexes;
    }
    public void show(){
        System.out.printf("\t");
        for (int i = 0;i<vertexes.length;i++){
            System.out.printf("%s\t",vertexes[i]);
        }
        System.out.println();
        for(int i = 0; i<edges.length;i++){
            System.out.printf("%s\t",vertexes[i]);
            for (int j = 0;j<edges.length;j++){
                System.out.printf("%d\t",edges[i][j]);
            }
            System.out.println();
        }
    }

}

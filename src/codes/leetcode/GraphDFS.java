package codes.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GraphDFS {
    public static void main(String[] args){
        Solution s = new Solution();
        int[][] graph ={{4,3,1},{3,2,4},{3},{4},{}};//[[4,3,1],[3,2,4],[3],[4],[]]
        System.out.println(s.allPathsSourceTarget(graph));
    }
}
class Solution {
    List<List<Integer>> total = new LinkedList<List<Integer>>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        //bfs存路径比较麻烦直接用dfs,bfs没有必要
        //dfs用到回溯和递归
        //1.对应的是否访问过的数组
        boolean isVisited[] = new boolean[graph.length];
        //2.用栈数据结构存，可以尝试去初始化一个list使用的方法就是list.append()和list.pop()
        //3.想办法存到一个列表里面
        isVisited[0] = true;
        for(int i = 0 ; i < graph[0].length ; i++){
            List<Integer> list = new LinkedList<Integer>();
            list.add(0);
            isVisited[graph[0][i]] = true;
            list.add(graph[0][i]);
            dfs(graph, isVisited, graph[0][i],list);
            isVisited[graph[0][i]] = false;
        }
        return total;
    }
    public void dfs(int[][] graph, boolean[] isVisited, int step, List<Integer> list ){
        //如果step是对应到的n-1那就返回输出
        if(step == graph.length - 1){

            total.add(new ArrayList<Integer>(list));
//            for (boolean a: isVisited){
//                System.out.print(a+"  ");
//            }
//            System.out.println();
            isVisited[step] = false;
            return;
        }
        for(int i = 0 ; i < graph[step].length ; i++){
            if( isVisited[graph[step][i]] == false){
                isVisited[graph[step][i]] = true;
                list.add(graph[step][i]);
                dfs(graph, isVisited, graph[step][i],list);
                list.remove(list.size()-1);
                isVisited[graph[step][i]] = false;
            }
        }
    }
}
//注意变量管理的问题，也就是说如果传入引用指针的话，后面的更改会造成问题
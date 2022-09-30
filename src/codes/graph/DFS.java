package codes.graph;

public class DFS {
    /**
     * 回溯和dfs的时候，注意用栈存的时候要在循环体里面进行实现三个操作
     * add -----> dfs -----> remove
     * 上述一般要在循环体里面实现，
     * 退出条件一般不对某单个元素操作，而是把结果打印出来
     * 所以不能对于某单个元素进行操作，如下图
     * void dfs(int[] candidates, int target, List<Integer> list, List<List<Integer>> ans){
     *         if(target == 0) {
     *             List<Integer> e = new ArrayList<>();
     *             e.addAll(list);
     * * * * * *   ans.add(e);
     * * * * * *   return;
     *         }
     *         if(target < 0){
     * * * * * *   return;
     *         }
     *         for(int i = 0; i < candidates.length; ++i) {
     * * * * * *   list.add(candidates[i]);
     * * * * * *   dfs(candidates, target - candidates[i], list, ans);
     * * * * * *   list.remove(list.size() - 1);
     *         }
     *     }
     */
/*
    int check(参数)
    {
        if(满足条件)
            return 1;
        return 0;
    }

    void dfs(int step)
    {
        判断边界
        {
            相应操作
        }
        尝试每一种可能 加一个循环 --- 回溯
        {
            满足check条件
                    标记 -- 需要一个标记数组，和韩老师讲得是一样的，注意到韩老师也设置了一个boolean类型的数组
            继续下一步dfs(step+1)
            恢复初始状态（回溯的时候要用到）--意思是如果是所有的路径都要找到，加上这一步，如果没有的话就不加
        }
    }
    八皇后没有涉及到上面的问题原因是直接在judgement里面体现出来不需要设置标记数组和恢复原始状态，但是需要的话也可以加上
    回溯往往用栈存(深度优先搜索) --把结果存起来
 */
}

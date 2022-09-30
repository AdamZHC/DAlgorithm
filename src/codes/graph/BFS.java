package codes.graph;

public class BFS {
    //就是通过队列的思想来完成树的遍历，所以说是一样的，也就是一层一层来遍历
    //当然也需要记录每个被访问的结点boolean[] vis,当然这时候就不存在什么最优路径和回溯
    //从某一点开始按照顺序来入队列，一层一层往下找，没有最优路径，路径都是一样的，也不会回溯，这一层遍历完了就是完了，不会改变初始结点
    //没有更多路径，当然也就没有最优这一说法
    //广度优先搜索不需要递归，结束的条件就是入队后进行循环，循环结束条件就是队列再次为空注意最后有一个一旦无子结点
    // if-else前者要判断队列是否为空，否则就继续进行该下一个结点，注意有两层循环
    //外层循环就是说一个一个出队列，内层循环就是不断把，某结点出完队列的对应的若干个邻接结点填入到队列中，再执行下一步循环
    //也就是说只执行一把，出队列然后把出去的这个结点对应的邻接结点入队即可，也就是说内层循环一结束，只是往下走了一层
    /*
    * while (!codes.queue.isEmpty()) {

            Node node = codes.queue.poll();//队列头结点出队

            visite(node);

            Set<Node> set = node.getSet();//获取所有的直接关联的节点

            Iterator<Node> iterator = set.iterator();

            while(iterator.hasNext()) {

                Node next = iterator.next();

                if (!visite.contains(next)) {//不包含说明没有没有被访问

                    codes.queue.add(next);

                    visite.add(next);
                }

            }
        }
*/
}

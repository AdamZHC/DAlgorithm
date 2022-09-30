package codes.leetcode;

import java.util.*;

public class LeetCode502 {
    public static void main(String[] args){
        int[] profits = {1,2,3};
        int[] capital = {0,1,2};
        System.out.println(new Solution502().findMaximizedCapital2(10,0,profits,capital));
    }
}
//比较器里面都是待比较的数据结构，没有规定在大的容器中的比较直接规定，都是通过比较器来实现
//可以通过实现比较接口来实现比较，还有一个比较方便的方法就是定义一个比较器直接规定比较规则
//注意比较器和比较接口的泛型都是待比较的数据结构，最后把要使用的排序容器中扔一个比较器
class Solution502 {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        //对成本排一下序，然后把时间复杂度讲到NlogN
        Comparator<List<Integer>> comparator = new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(0) - o2.get(0);
            }
        };
        //需要用到排序的时候就是使用比较器
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        for(int i = 0; i < capital.length ; i ++){
            List<Integer> element = new ArrayList<Integer>();
            element.add(profits[i]);
            element.add(capital[i]);
            lists.add(element);
        }
        lists.sort(comparator);
//        lists.remove(0);
        System.out.println(lists.get(0));
        //因为add之后返回布尔值所以不能直接用匿名对象
        // for(int i = 0 ; )
        // boolean isInvested[] = new boolean[capital.length];
        // //无成本
        // for(int i = 0 ; i < k ; i ++ ){
        //     int max = 0, max_index = 0;
        //     for(int j = 0 ; j < capital.length ;j++){
        //         if( w >= capital[j] && isInvested[j] == false){
        //             if( profits[j] > max ){
        //                 max = profits[j];
        //                 max_index = j;
        //             }
        //         }
        //     }
        //     isInvested[max_index] = true;
        //     w += max;
        // }
        // return w;
        return 1;
    }
    public int findMaximizedCapital2(int k, int w, int[] profits, int[] capital) {
        //使用堆，保证堆中存放着小于投资的项目
        //比较器统一使用
        Comparator<List<Integer>> comparatorList = new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(0) - o2.get(0);
            }
        };
        Comparator<List<Integer>> comparatorQueue = new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o2.get(1) - o1.get(1);
            }
        };
        //初始化优先队列和数组
        PriorityQueue<List<Integer>> queue = new PriorityQueue<List<Integer>>(comparatorQueue);
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        //true表示可以不插入，但是只要有一个就可以插入
        boolean flag = true;
        for(int i = 0 ; i < capital.length ;i ++){
            List<Integer> list = new ArrayList<Integer>();
            list.add(capital[i]);
            list.add(profits[i]);
            lists.add(list);
            if( w >= capital[i]){
                flag = false;
            }
        }
        if(flag) return w;
        lists.sort(comparatorList);
        for(int i = 0 ,j = 0; i < k ; i ++){
            //一直保持出来的是最大的
            if(j > capital.length - 1 ) break;
            while( j < capital.length && lists.get(j).get(0) <= w ){
                queue.add(lists.get(j));
                j++;
            }
            w += queue.remove().get(1);

        }
        return w;
    }
}


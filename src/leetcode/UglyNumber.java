package leetcode;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class UglyNumber {
    public static void main(String[] args){
        //利用优先队列(小根堆),把可能的情况一一保证列举出来，而且
        //有自动排序的功能，直接把大小顺序排出来，2/3/5，每一次都可以排出来
        //注意去重，每次把最小的pop出来，这样才能实现队列的效果，类似与BFS,
        //这样大根堆数据结构往往和优先队列结合起来，就是利用BFS的思想
        //所以单用根堆这种的数据结构一般还是排序，常见的还是优先队列，
        //一个一个pop出来然后再处理，最终再push进去，这就是优先队列，底层实现就是堆排序
        new Solution1202().nthUglyNumber(10, 2, 3,5);
    }
}
class SolutionPQ {
    public int nthUglyNumber(int n) {
        int[] factors = {2, 3, 5};
        Set<Long> seen = new HashSet<Long>();
        PriorityQueue<Long> heap = new PriorityQueue<Long>();
        seen.add(1L);
        heap.offer(1L);
        int ugly = 0;
        //从n=1开始一直到目的n，然后先把队头出列，出列的元素乘以2、3、5
        //入队（底层实现排序）最终一直出队到自己所需要的元素，保证了每个丑数的元素
        //都可以涉及到，保证都能入优先队列，最终把返回，但是这样会多入队元素造成时间复杂度太多
        for (int i = 0; i < n; i++) {
            long curr = heap.poll();
            ugly = (int) curr;
            for (int factor : factors) {
                long next = curr * factor;
                if (seen.add(next)) {
                    heap.offer(next);
                }
            }
        }
        return ugly;
    }
}
class SolutionDP {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        //注意这时的p1,p2,p3是索引下标，不是具体的丑数值
        //p1,p2,p3是最小的下标，然后一直到目的下表值，
        //这个思路也是把之前最小的选出来，每次之前的乘以2,3,5,之后
        //把这个选到最小的，然后把对应的指针移动表示已经用过了，表示2
        //又用到了一个后期指针的移动可以保证最小的那个的三个比较，三指针移动
        //不一定是哪个往前移动但是每次会移动会确定一个，每次往前确定一个，
        //所以每次设三个指针不一定是哪一个，但是一定是最小的，注意这若干个p是
        //最小的值的下标
        //我懂了，这三个是最小值的下标，得到之后往后++，这样每一个都++可以保证每次
        //的一个因数都不错过，这个++没有特殊的意思就是保证不会对一个下标的值重复赋值，
        //就是一个一个保证每个dp最小值都能接触到，设置三个指针就是保证2,3,5三个因数
        //都能让这个dp接触到
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(num2, num3), num5);
            if (dp[i] == num2) {
                p2++;
            }
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }
        }
        return dp[n];
    }
}
class Solution1202 {
    public int nthUglyNumber(int n, int a, int b, int c) {
        //简单题可以用新思路，但是要是正确的思路，就像之前那个题没有必须要
        //一想到是所有的因数1，2，3，4，5而不是只有3和5为因数，这也就没比要用堆做了，不过也相当于复习了
        //--------------------------------//
        //猜测因为第几个是更小的那个被整除的决定的
        //这时候要考虑的就是只要能整除就行，能被大的整除的，肯定
        //能被小的整除，所以只要一个能整除就行，所以无所谓了
        //所以当不互质的情况的时候那就直接，用到较小那个有合数
        //那个树使用堆的思路既就可以了
        //优先队列初始化就是最小堆
        //所以这时不需要比较器函数Comparator()
        //现在假设都是互质的情况
        PriorityQueue <Integer> p = new PriorityQueue<>();
        p.add(1);
        int cnt = 0;
        while(cnt < n){
            int element = p.remove();
            if(!p.contains(a * element)){
                p.add(a * element);
            }
            if(!p.contains(b * element)){
                p.add(b * element);
            }
            if(!p.contains(c * element)){
                p.add(c * element);
            }
            cnt++;
        }
        return p.remove();
    }
}


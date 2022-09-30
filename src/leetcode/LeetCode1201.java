package leetcode;

import java.util.*;

public class LeetCode1201 {
    public static void main(String[] args) {
        new Solution1201().nthUglyNumber(10,2,3,5);
    }
}
class Solution1201 {
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
        //--------------------------------//
        //只能枚举
        //假设都是互质的情况(除1外)
        if(a == 1 || b == 1 || c == 1) return n;
        // //获取三个数字排序之后
        //如果是最小的数只有一个数的时候
        //如果有两个数的时候
        //--------------------------------//
        //不需要用到堆
        //直接用set去重，然后使用注意考虑怎么对于多出来的数--就可以了
        // 0 -- A, 1 -- B, 2 -- C
        //历经千辛万苦之后，终于思考出正确的解法了！！！
        //由于最小堆不是丑数II的最优思路，所以导致这个问题有一些阻碍，其实使用多路归并的动态规划
        //思想就可以把这部分问题处理好
        int A = Math.min(Math.min(a, b), c);
        int C = Math.max(Math.max(a, b), c);
        int B = (a + b + c) - A - C;
        //以上是三者排好序的情况A最小的，B是中间的，C是最大的
        //从这个数组开始倒序枚举
        int[] points = new int[3];
        points[0] = A * n;
        points[1] = points[0] / B * B;
        points[2] = points[1] / C * C;
        //这里根据哈希散列的思想记录三个值
        int[] step = new int[3];
        step[0] = A; step[1] = B; step[2] = C;
        //在这里需要确定好cnt的个数，关键一点就是去掉重复的数
        //鉴于此，我在这里生成一个重复数集合
        //使用动态规划与多路归并的思想
        //这边还要记录出现了几次
        List<Integer> repeats = new ArrayList<>();
        List<Integer> countsRepeats = new ArrayList<>();
        repeats.add(1); countsRepeats.add(0);
        int pA = 0, pB = 0, pC = 0;
        while(true){
            int element = Math.min(Math.min(repeats.get(pA) * A, repeats.get(pB) * B), repeats.get(pC) * C);
            if(element >= points[0]) break;
            if(element % A == 0 && element % B != 0 && element % C != 0){
                countsRepeats.add(1);
            }else if(element % A != 0 && element % B == 0 && element % C != 0){
                countsRepeats.add(1);
            }else if(element % A != 0 && element % B != 0 && element % C == 0){
                countsRepeats.add(1);
            }else if(element % A != 0 && element % B == 0 && element % C == 0){
                countsRepeats.add(2);
            }else if(element % A == 0 && element % B != 0 && element % C == 0){
                countsRepeats.add(2);
            }else if(element % A == 0 && element % B == 0 && element % C!= 0){
                countsRepeats.add(2);
            }else if(element % A == 0 && element % B == 0 && element % C == 0){
                countsRepeats.add(3);
            }
            repeats.add(element);
            if(element == repeats.get(pA) * A) {
                pA++;
            }
            if(element == repeats.get(pB) * B) {
                pB++;
            }
            if(element == repeats.get(pC) * C) {
                pC++;
            }
        }
        //得到去重集合之后可进行对于cnt的改造了 --- 先不对其改造，否则会出问题
        //实际上就是指，在当前指向中有几个是多出来的是要实施去重的
        //比如说目前有n个数，其实不代表是第n个丑数，代表n个丑数中还有重复的
        //情况，那这时比如说我有k个重复的，那第target + k就是目的我要找到的丑数了
        int cnt = points[0] / B + points[1] / C - repeats.size();
        //Set<Integer> set = new HashSet<>();
        //每次要看是否能整除factor，然后再去考量
        //之后就是将其看作一个HashMap处理
        while (cnt > 0) {
            int point = query(points[0], points[1], points[2]);
            if (repeats.contains(points[point])) {
                int idx = repeats.indexOf(points[point]);
                if (countsRepeats.get(idx) == 3) {
                    countsRepeats.set(idx, 2);
                } else if (countsRepeats.get(idx) == 2) {
                    countsRepeats.set(idx, 1);
                } else if(countsRepeats.get(idx) == 1){
                    countsRepeats.set(idx, 0);
                    repeats.remove(idx);
                    cnt--;
                }
            } else {
                cnt--;
            }
            points[point] -= step[point];
        }
        int ans = query(points[0], points[1], points[2]);
        return points[ans];
    }
    int query(int pointA, int pointB, int pointC) {
        int max = Math.max(Math.max(pointA, pointB), pointC);
        if(max == pointA){
            return 0;
        }else if(max == pointB){
            return 1;
        }else{
            return 2;
        }
    }
}
package codes.leetcode;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class L1360 {
    public static void main(String[] args) {
        String s = "123";
        String r = s.substring(0, 3);

    }
    static boolean legal(String s) {
        if(s.length() == 2)
            if((s.charAt(0) == 1 || s.charAt(0) == 2) && s.charAt(1) >= 0 && s.charAt(1) <= 6)
                return true;
        return false;
    }
}
class Code1360{
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        if(blocked.length < 2) return true;
        int[][] di = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        //BFS + 堆优化
        //坐标就用P类表示——和结构体的思想类似
        PriorityQueue<P> pq = new PriorityQueue<>((o1, o2) -> pd(o1, o2));
        //用HashSet表示对应是否访问
        Set<P> vis = new HashSet<>();
        for(int[] b : blocked){
            P pos = new P(b[0], b[1]);
            vis.add(pos);
        }
        P tp = new P(target[0], target[1]);
        //入队前就进行标记已经访问过
        P sp = new P(source[0], source[1]);
        vis.add(sp);
        //对进行入队
        pq.offer(sp);
        while(!pq.isEmpty()){
            System.out.println(pq);
            P op = pq.poll();
            if(op.equals(tp)) return true;
            for(int i = 0; i < 4; ++i) {
                P np = new P(op.getX() + di[i][0], op.getY() + di[i][1]);
                //没有访问过且合法，另外是提前入集合说明已经被封锁
                if(check(np) && !vis.contains(np)) {
                    vis.add(np);
                    pq.offer(np);
                }
            }
        }
        return false;
    }

    //简而言之——就是对应的两个坐标之间的距离
    int pd(P p1, P p2) {
        long[] a1 = p1.pos;
        long[] a2 = p2.pos;
        return (int) (Math.sqrt(a1[0] * a1[0] + a1[1] * a1[1] - a2[0] * a2[0] - a2[1] * a2[1]));
    }

    //验证是否合法
    boolean check(P p) {
        return p.pos[0] >= 0 && p.pos[0] < 1000000L && p.pos[1] >= 0 && p.pos[1] < 1000000L;
    }

}
//可以实现对应的比较接口
class P {
    long[] pos = new long[2];
    public P (long x, long y){
        pos[0] = x;
        pos[1] = y;
    }
    //获取对应的坐标
    public long getX() {
        return pos[0];
    }

    public long getY() {
        return pos[1];
    }

    @Override
    public boolean equals(Object o){
        P p = (P)o;
        return p.pos[0] == pos[0] && p.pos[1] == pos[1];
    }

    @Override
    public int hashCode(){
        return (int)(1000L * pos[0] +  pos[1]);
    }

    @Override
    public String toString() {
        return "X:" + pos[0] + " Y:" + pos[1];
    }
}
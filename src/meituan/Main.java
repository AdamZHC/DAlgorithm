package meituan;
import java.util.*;
class Main {
    static Scanner in = new Scanner(System.in);
    static int n;
    static int[] w, prfx;
    public static void main(String[] args) {
        Deque<Integer> q = new ArrayDeque<>();
        String[] ss = "asdsdsdf".split("afd", 1);
        read();
        prfx = new int[n + 1];
        //前缀和
        for(int i = 1; i <= n; ++i)
            prfx[i] = prfx[i - 1] + w[i];

        PriorityQueue<PII> pq = new PriorityQueue<>((o1, o2) -> (prfx[o2.r] - prfx[o2.l - 1]) - (prfx[o1.r] - prfx[o1.l - 1]));
        Set<Integer> set = new HashSet<>();
        pq.offer(new PII(1, n));
        for(int i = 0; i < n; ++i) {
            int k = in.nextInt();
            set.add(k);
            PII p = pq.peek();
            //延迟删除要在最开始
            Set<Integer> tmp = new HashSet<>();
            tmp.addAll(set);
            for(int e : tmp) {
                if(!pq.isEmpty() && p.l <= e && p.r >= e) {
                    pq.poll();
                    if(e != p.l)
                        pq.offer(new PII(p.l, e - 1));
                    if(e != p.r)
                        pq.offer(new PII(e + 1, p.r));
                    p = pq.peek();
                    set.remove(e);
                }
            }
            System.out.println(p == null ? 0 : prfx[p.r] - prfx[p.l - 1]);
        }
    }
    static void read() {
        n = in.nextInt();
        w = new int[n + 1];
        for(int i = 1; i <= n; ++i)
            w[i] = in.nextInt();
    }
}
class PII {
    int l, r;
    public PII(int l, int r) {
        this.l = l; this.r = r;
    }
}

package luogu;

import java.util.PriorityQueue;
import java.util.Scanner;

public class P1168 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; ++i) {
            arr[i] = in.nextInt();
        }
        if(n == 0)
            return;
        //使用对顶堆
        PriorityQueue<Integer> Max = new PriorityQueue<>((o1, o2) -> o2 - o1);
        PriorityQueue<Integer> Min = new PriorityQueue<>();
        int ans = arr[0];
        System.out.println(ans);
        for(int i = 1; i < n - 1; i += 2) {
            //对顶堆的维护需要和中位数比较
            int a = arr[i];
            int b = arr[i + 1];
            if(a >= ans && b <= ans) {
                Max.offer(b);
                Min.offer(a);
            } else if(b >= ans && a <= ans) {
                Max.offer(a);
                Min.offer(b);
            } else if(b >= ans && a >= ans) {
                Max.offer(ans);
                Min.offer(a);
                Min.offer(b);
                ans = Min.poll();
            } else {
                Min.offer(ans);
                Max.offer(a);
                Max.offer(b);
                ans = Max.poll();
            }
            System.out.println(ans);
        }
    }
}

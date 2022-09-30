package lanqiao;

import java.util.*;

public class C7 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int t = in.nextInt();
        int[][] orders = new int[m][2];
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < 2; ++j) {
                orders[i][j] = in.nextInt();
            }
        }
        Arrays.sort(orders, (o1, o2) -> o1[1] - o2[1]);
        int cnt[] = new int[n + 1];
        for(int[] o : orders) {
            cnt[o[1]]++;
        }
        boolean isPriority[] = new boolean[n + 1];
        //不需要去模拟，只要模拟统计一下就可了
        int p = 0;
        for(int i = 1; i <= n && p < m; ++i){
            while(p < m && orders[p][1] < i) p++;
            if(orders[p][1] != i) continue;
            List<Integer> orderDetail = new ArrayList<>();
            for(int k = 0; p < m && k < cnt[i]; ++k) {
                orderDetail.add(orders[p + k][0]);
            }
            int priority = 0;
            //表示时间的
            for(int k = 1; k <= t; ++k) {
                int tot = 0;
                for(int num : orderDetail) {
                    if(num == k) tot++;
                }
                if(tot == 0){
                    if(priority > 0) priority -= 1;
                }else{
                    priority += 2 * tot;
                }
                if(priority > 5) isPriority[i] = true;
                if(priority <= 3) isPriority[i] = false;
            }
        }
        int ans = 0;
        for(int i = 0; i < isPriority.length; ++i) {
            ans += isPriority[i] ? 1 : 0;
        }
        System.out.println(ans);
    }
}

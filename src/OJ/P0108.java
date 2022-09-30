package OJ;

import java.util.*;

public class P0108 {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] candy = new long[n + 1];
        for(int i = 1; i <= n; ++i)
            candy[i] = in.nextLong();
        long avg = 0;
        for(int i = 1; i <= n; ++i){
            avg += candy[i];
        }
        avg /= n;
        long[] m = new long[n + 1];
        for(int i = 1; i <= n; ++i)
            m[i] = m[i - 1] + avg - candy[i - 1];
        Arrays.sort(m);
        long mid = m[(n + 1) / 2];
        long ans = 0;
        for(int i = 1; i <= n; ++i)
            ans += Math.abs(mid - m[i]);
        System.out.println(ans);

    }
    //    scanf("%lld",&n);
//	for(int i=1;i<=n;++i)
//    scanf("%lld",&a[i]),ave+=a[i];
//    ave/=n;
//	for(int i=1;i<=n;++i)
//    c[i]=c[i-1]+ave-a[i-1];
//    sort(c+1,c+n+1);
//    mid=c[(n+1)/2];
//	for(int i=1;i<=n;++i)
//    ans+=abs(mid-c[i]);
//    printf("%lld",ans);
//	return 0;
}

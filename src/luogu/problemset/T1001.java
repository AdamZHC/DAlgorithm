package luogu.problemset;


import java.util.Arrays;
import java.util.Scanner;

public class T1001 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        //获取素数列表
        int[] prime = P(n);
        int[] arr = new int[(int)Math.sqrt(n) + 1];
        int temp = n;
        for(int i = 0; temp > 1; ++i) {
            for(int k = 0; true; ++k) {
                if(temp % prime[k] == 0) {
                    temp /= prime[k];
                    break;
                }
            }
            arr[i] = temp;
        }
        int ans = 0;
        for(int i = 0; i < arr.length; ++i)
            ans += arr[i];
        System.out.println(ans);
    }
    public static int[] P(int n){
        //素数筛
        int[] prime = new int[n];
        boolean [] vis = new boolean[n * 3 + 5];
        int cnt = 0;
        for(int i = 2; i < n * 3; i++) {
            if(!vis[i]){
                //把i加入对应的素数集合
                prime[cnt++] = i;
                vis[i] = true;
            }
            for(int j = 0; j < cnt; ++j) {
                if(i * prime[j] > n * 3)
                    break;
                vis[i * prime[j]] = true;
                if(i % prime[j] == 0)
                    break;
            }
        }
//        System.out.println(Arrays.toString(prime));
        return prime;
    }
}
//      int prime[MAXN];
//      bool vis[MAXN];
//      int cnt=0;
//      void Euler_prime(int n) {
//          for(int i=2;i<=n;++i) {
//              if(!vis[i]){
//                  prime[cnt++]=i;
//                  vis[i]=true;
//              }//vis[i]置为true或不置true都可以
//              for(int j=0;j<cnt;++j) {
//                  if(i*prime[j]>n)//判断是否越界
//                      break;
//                  vis[i*prime[j]]=true;//筛数
//                  if(i%prime[j]==0)//时间复杂度为O(n)的关键！
//                      break;
//               }
//          }
//      }
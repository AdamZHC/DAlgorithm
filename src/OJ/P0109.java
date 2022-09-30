package OJ;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class P0109 {
    public static void main(String[] args){
        //二分经典思路
        //类似之前游泳池的BFS + 二分 还有容斥原理
        Scanner in =  new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] oxRooms = new int[n];
        for(int i = 0; i < n; ++i)
            oxRooms[i] = in.nextInt();
        //自动排序,不需要加比较器
        Arrays.sort(oxRooms);
        //左右上下界
        int left = 0;
        int right = oxRooms[n - 1] - oxRooms[0];
        int mid = 0;
        int ans = 0;
        while(left <= right){
            //二分处理
            mid = left + (right - left) / 2;
//            System.out.printf("mid=%d\n", mid);
            if(check(oxRooms, mid, m)){
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(ans);
    }
    //二分条件
    static boolean check(int oxRooms[], int d, int oxCnt){
        int n = oxRooms.length;
        int step = 1;
        int p = oxRooms[0];
        for(int i = 1; i < n; ++i){
            if(oxRooms[i] - p >= d){
                step++;
                p = oxRooms[i];
            }
            if(step == oxCnt) return true;
        }
        return false;
    }
}
//        #include<bits/stdc++.h>
//        using namespace std;
//        const int MAXN = 1e5+5;
//        int a[MAXN];
//        int n,m;
//        bool judge(int dis){
//        int num=1,p=a[0];
//        for(int i=1;i<n;i++){//搜索是否满足条件
//        if(a[i]-p>=dis){
//        num++;
//        p=a[i];
//        if(num==m)  return true;
//        }
//        }
//        return false;
//        }
//        int main(){
//        scanf("%d%d",&n,&m);
//        for(int i=0;i<n;i++)  scanf("%d",&a[i]);
//        sort(a,a+n);
//        int l=0,r=a[n-1]-a[0];
//        int mid;
//        int ans;
//        while(l<=r){
//        mid=(l+r)>>1;
//        if(judge(mid)){
//        ans=mid;
//        l=mid+1;
//        }
//        else r=mid-1;
//        }
//        printf("%d\n",ans);
//        return 0;
//        }

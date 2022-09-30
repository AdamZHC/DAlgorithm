package lanqiao;

import java.util.*;

public class C7AC {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int t=sc.nextInt();
        HashMap<Integer,Integer> arr[]=new HashMap[t+1];
        for(int i=0;i<t+1;i++){
            arr[i]=new HashMap<Integer,Integer>();
        }
        int id,ti;
        for(int i=0;i<m;i++){
            ti=sc.nextInt();
            id=sc.nextInt();
            if(arr[ti].containsKey(id)){
                arr[ti].put(id, arr[ti].get(id)+1);
            }else{
                arr[ti].put(id, 1);
            }
        }
        int last[]=new int[n+1];
        int scor[]=new int[n+1];
        int prio[]=new int[n+1];
        for(int i=0;i<=t;i++){
            for(int temp:arr[i].keySet()){
                scor[temp]=Math.max(scor[temp]-(i-last[temp]-1),0);
                if(scor[temp]>5&&prio[temp]==0){
                    prio[temp]=1;
                }else if(scor[temp]<4&&prio[temp]==1){
                    prio[temp]=0;
                }
                scor[temp]+=arr[i].get(temp)*2;
                if(scor[temp]>5&&prio[temp]==0){
                    prio[temp]=1;
                }
                last[temp]=i;
            }
        }
        for(int i=0;i<=n;i++){
            scor[i]=Math.max(scor[i]-(t-last[i]),0);
            if(scor[i]<4&&prio[i]==1){
                prio[i]=0;
            }
        }
        int ans=0;
        for(int i=0;i<=n;i++){
            ans+=prio[i];
        }
        System.out.println(ans);
    }
}



package codes.zwj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class hole{
    int x,y,z;
    int flag=0;
    public  hole(int x,int y,int z){
        this.x=x;
        this.y=y;
        this.z=z;
    }

}
public class Main {

    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i =0;i<T;i++){
            one(sc);}

    }
    public static void one(Scanner sc){


        int n = sc.nextInt();
        int h =sc.nextInt();
        int r =sc.nextInt();
        ArrayList<hole> set = new ArrayList();
        ArrayList<hole> start = new ArrayList<>();
        for (int i =0;i<n;i++){

            hole temp =new hole(sc.nextInt(),sc.nextInt(), sc.nextInt());
            if (temp.z<=r){
                start.add(temp);
            }
            else{
                set.add(temp);}

        }
        boolean ans =false;
        for (int i =0;i<start.size();i++){
            ans =dfs(start.get(i),r,h,set);
            if (ans ){
                break;
            }
        }
        if (ans){
            System.out.println("Yes");
        }
        else
            System.out.println("No");
    }

    public static boolean near(hole a,hole b,int r ){
        double d= Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y)+(a.z-b.z)*(a.z-b.z));
        if (d<=2*r){
            return true;
        }
        else return false;
    }
    public static boolean dfs(hole cur,int r,int h,ArrayList<hole> set){
        if (cur.z+r>=h){
            return true;
        }
        cur.flag=1;
        for (int i =0;i<set.size();i++){
            if (set.get(i).flag!=1 && near(cur,set.get(i),r)){
                hole next = set.get(i);
                next.flag = 1;
                boolean OK = dfs(next,r,h,set);

                if (OK){
                    return true;
                }
            }
        }
        return false;
    }
}


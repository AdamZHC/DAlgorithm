package OJ;

import java.util.Scanner;

public class P0110 {
//    static char[] s = new char[1000005];
    static int[] next = new int[1000005];
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        while(!str.equals(".")){
            int len = str.length();
            getNext(str);
            if(len % (len - next[len]) == 0)
                System.out.println(len/(len-next[len]));
            else
                System.out.println(1);
            str = in.nextLine();
        }
    }
    static void getNext(String a){
        int len = a.length();
        int i = 0, j = -1;
        next[0] = -1;
        while(i < len){
            if(j == -1 || a.charAt(i) == a.charAt(j))
                next[++i] = ++j;
            else
                j = next[j];
        }
    }
}
//#include<cstdio>
//#include<iostream>
//#include<algorithm>
//#include<cstring>
//using namespace std;
//        char s[1000005];
//        int next[1000005];
//        void getnext(char *a)
//        {
//        int len=strlen(a);
//        int i=0,j=-1;
//        next[0]=-1;
//        while(i<len)
//        {
//        if(j==-1||a[i]==a[j])
//        next[++i]=++j;
//        else j=next[j];
//        }
//        }
//        int main()
//        {
//        while(scanf("%s",s)!=EOF&&s[0]!='.')
//        {
//        int len=strlen(s);
//        getnext(s);
//        if(len%(len-next[len])==0)
//        printf("%d\n",len/(len-next[len]));
//        else
//        printf("1\n");
//        }
//        return 0;
//        }



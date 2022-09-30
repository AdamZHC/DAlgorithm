package basic.algorithm;

import java.util.Arrays;

public class KMPDemo {
    //暴力匹配的时候有一个回溯，也就是说需要往回走，这时匹配到i和j的时候，如果不匹配的话--> i = i - (j - 1), j = 0这样就可以
    //也就是相当于i在最初匹配的地方往后移动一位,j就置为0
    public static void main(String[] args){//静态方法不能调用直接调用非静态的，必须先创建该类的对象
//        System.out.println(Arrays.toString(partialMcTab("abababca")));
//        System.out.println(kmp("sdsdfewewasasdas","asa"));
        System.out.println(Arrays.toString(partialMcTab("abababca")));
    }
    //关键就是这个部分匹配表
    public static int[] partialMcTab(String s) {
        int[] tab = new int[s.length()];
        for (int slow = 0, fast = 1, cnt = 0; fast < s.length(); fast++) {
            if (s.charAt(slow) == s.charAt(fast)) {
                tab[fast] = ++cnt;
                slow++;
            } else {
                cnt = 0;
                tab[fast] = 0;
            }
        }
        return tab;
    }
    public static int kmp(String dst,String src) {
        int [] tab =partialMcTab(src);
        int i = 0, j = 0;
        while( j < src.length() && i < dst.length()) {
            if(dst.charAt(i) == src.charAt(j)){
                i++;
                j++;
            }else{
                i = i - tab[j]+1;//加1没有变，因为不匹配所以要接着进行，生活要继续，但是回退的个数要变化，加1意思是往下一步
                //而且本来第一个肯定是0，要是第一个就不匹配，那就回退0个，也就是说一直不变不加1，不往后进行肯定不可以
                j = 0;
            }
        }
        return j == src.length() ? i-src.length() : -1;
    }
}

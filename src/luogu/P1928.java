package luogu;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class P1928 {
    private static String origin = "";
    private static int p = -1;
    public static void main(String[] args) {
        int[] temp = {1,2,23,2,3,1};
        Scanner in = new Scanner(System.in);
        origin = in.nextLine();
        String s = creat();
        System.out.println(s);

    }
    static String creat() {
        String ans = "";
        char ch = '#';
        int cnt = 0;
        while(p + 1 < origin.length()) {
            ch = origin.charAt(++p);
            if(ch == '[') {
                while(origin.charAt(p + 1) >= '0' && origin.charAt(p + 1)<= '9'){
                    cnt = cnt * 10 + origin.charAt(++p) - '0';
                }
                String s = creat();
                while(cnt-- != 0){
                    ans += s;
                }
                cnt = 0;
            }else if(ch == ']'){
                return ans;
            }else {
                ans += ch;
            }
        }
        return ans;
    }
}

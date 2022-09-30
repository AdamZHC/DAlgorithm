package luogu;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        int[] tc = new int[300];
        Random r = new Random();
        for(int i = 0; i < 300; ++i){
            tc[i] = Math.abs(r.nextInt(999)) + 1;
        }
        System.out.println(Arrays.toString(tc));
    }
    static  List<String> sp (String expression) {
        List<String> ss = new LinkedList<>();
        int n = expression.length();
        int i = 0;
        while(i < n) {
            String num = "";
            while(i < n && isNumber(expression.charAt(i))) {
                num += expression.charAt(i++);
            }
            ss.add(num);
            if(i < n)
                ss.add(expression.charAt(i++) + "");
        }
        return ss;
    }
    static boolean isNumber(char ch) {
        return ch >= '0' && ch <= '9';
    }
}
class L {
    int[] arr;
    int n;

    public L (int[] arr){
        this.arr = arr;
        this.n = arr.length;
    }

    public L getPlusOne(int i){
        int[] newOne = Arrays.copyOf(arr, n);
        newOne[i]++;
        return new L(newOne);
    }
    @Override
    public boolean equals(Object o){
        int[] arr2 = ((L)o).arr;
        for(int i = 0; i < n; ++i)
            if(arr2[i] != arr[i])
                return false;
        return true;
    }
}

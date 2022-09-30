package lanqiao;

import java.util.*;

public class MEX {
    public static void main(String[] args) {
        //记忆化查找
        //是否为闰年
        int month = 0;
        int day = 0;
//        int monthDays[] = {31,28,30,31}
        Set<Integer> set = new TreeSet<>();
    }
    public boolean isLeap(int year) {
        return year % 400 == 0 || (year % 100) == 0 && year % 4 != 0;
    }
}

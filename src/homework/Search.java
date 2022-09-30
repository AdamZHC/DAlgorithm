package homework;

import java.util.*;
import java.io.*;

public class Search {
    public static int cSearch(int[] a, int x, int low, int high) {
        int mid;
        if (low > high) return -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (x == a[mid]) return mid;
            else if (x < a[mid])
                high = mid - 1;
            else low = mid + 1;
        }
        return -1;
    }

    public static int bSearch(int[] a, int x, int low, int high) {
        int mid;
        if (low > high) return -1;
        mid = low + (high - low + 1) / 2;
        if (x == a[mid]) return mid;
        else if (x < a[mid])
            return bSearch(a, x, low, mid - 1);
        else
            return bSearch(a, x, mid + 1, high);
    }

    public static void main(String[] args) throws Exception {
        int[] a = new int[20];
        for (int i = 0; i < 20; i++)
            a[i] = i + 1;
        int t = cSearch(a, 5, 0, 19);
        if (t == -1)
            System.out.println("第一次查找失败");
        else
            System.out.println("第一次查找到数字在数组中的第" + (t + 1) + "位。");
        int s = cSearch(a, 30, 0, 19);
        if (s == -1)
            System.out.println("第二次查找失败");
        else
            System.out.println("第二次查找到数字在数组中的第" + (t + 1) + "位。");
        Random r = new Random();
        int[] b = new int[10000];
        for (int i = 0; i < 10000; i++)
            b[i] = r.nextInt();
        int h = b[9997];
        long startTime1 = System.currentTimeMillis();
        int m = bSearch(b, h, 0, 9999);
        if (m == -1)
            System.out.println("递归算法查找失败");
        else
            System.out.println("递归算法查找到数字在数组中的第" + (t + 1) + "位。");
        Long endTime1 = System.currentTimeMillis();
        System.out.println("递归算法运行时间是：" + (endTime1 - startTime1) + "毫秒");
        long startTime2 = System.currentTimeMillis();
        int n = cSearch(b, h, 0, 9999);
        if (n == -1)
            System.out.println("循环算法查找失败");
        else
            System.out.println("循环算法查找到数字在数组中的第" + (t + 1) + "位。");
        Long endTime2 = System.currentTimeMillis();
        System.out.println("循环算法运行时间是：" + (endTime2 - startTime2) + "毫秒");
    }
}


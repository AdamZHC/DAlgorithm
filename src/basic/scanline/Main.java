package basic.scanline;

import java.util.*;

public class Main {
    static Scanner in = new Scanner(System.in);
    //就是用HashMap维护就可以
    static Map<Integer, Set<String>> map = new HashMap<>();
    public static void main(String[] args) {
        int[] arr = {1, 4, 5, 2, 6, 3};
        quickSort(arr, 0, 5);
//        System.out.println(partition(arr, 0, 5));
        System.out.println(Arrays.toString(arr));
    }
    static void quickSort(int[] arr, int l, int r) {
        if(l >= r)
            return;
        int pi = partition(arr, l, r);
        quickSort(arr, l, pi - 1);
        quickSort(arr, pi + 1, r);
    }
    static int partition(int[] arr, int l, int r) {
        int pivot = l;

        while(l < r) {
            //这里不是等于号！而是直接小于号
            //等于号的话，会直接跳过需要的那个部分
            while(l < r && arr[l] < arr[pivot])
                l++;
            while(l < r && arr[r] > arr[pivot])
                r--;
            if(l == r)
                break;
            swap(arr, l, r);

        }
        swap(arr, l, pivot);
        return l;
    }
    static void swap(int[] arr, int a, int b) {
        if(a == b)
            return;
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];
    }
}
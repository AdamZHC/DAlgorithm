package basic.searchalgorithm;

import java.util.Arrays;

public class FibonacciSearch {
    static int maxSize = 20;
    public static void main(String[] args){
        System.out.println(Arrays.toString(f(5)));
    }
    public static int fibonacciSearch(int[] arr, int value){
        //用非递归的方法
        int low = 0;
        //不需要使high变作扩容之后更大的没有必要，那样会增加时间复杂度，运行时间
        int high = arr.length-1;
        int k = 0;
        int[] f=f(20);
        int mid=0;
        while(high > f[k]-1){
            //斐波那契数列中的就是用数值是用来表示扩容后的长度，而不是斐波那契数列的长度，是斐波那契数列中的值表示长度
            //因此对应的是high可以表示长度和斐波那契数列中的值f[k]进行比较
            k++;
        }
        int[] temp= Arrays.copyOf(arr,f[k]);//后面的是长度，不是索引，长度是斐波那契数列里面的值个，索引要减一
        //需要填充这部分把最大的填充到最后
        for(int i = high+1; i<f[k];i++){
            temp[i] = arr[high];
        }
        while(low <= high){
            mid = low + f[k-1]-1;
            if(value<temp[mid]){//找左边，找一边
                high = mid-1;//找一边，另一个不需要改，另一个要改
                k--;
            }else if( value >temp[mid]){
                low = mid+1;
                k-=2;
            }else{
                //因为补全所以可能找到一些超出范围的索引，所以要选择小一点的
                return mid>high?high:mid;
            }
        }
        return -1;
    }

    public static int[] f(int n) {
        int[] f = new int[n];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < f.length; i++) {//如果是length-1的话，会导致少赋值一个最后一个
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }
}

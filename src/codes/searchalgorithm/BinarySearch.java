package codes.searchalgorithm;

import java.util.ArrayList;
import java.util.Stack;

public class BinarySearch {
    public static void main(String[] args){
        Stack <Integer> stack = new Stack<>();
        int[] arr = {2,2,2,3,11,42,67,100000,19999999};
        System.out.println(""+binarySearch(arr,0,arr.length-1,0));
    }
    //这个函数的意思就是在对应的区间寻找需要遇到的索引
    //所以在整体没有找到就可以在左半部分或者右半部分找需要的索引
    //这种二分问题都会把中间的排除掉，在二分查找中是这样的，在快排中的二分思想，分治思想也是把中间的排除掉
    //也就是最后的就是判断语句if-else，l++,r--就是做这项工作

    public static int binarySearch(int [] arr, int left, int right, int value){
        int mid = (left+right)/2;
        //其实left和right相等的时候就是也可以用最下面的判断条件顺便判断，所以退出条件可以更优化
        //递归的思路可以是直接通过第1次的递归的思路来完成，也就是直接通过显示的代码的逻辑去理解
        //退出条件就是要么找到mid，要么找不到返回-1
        if(left > right){
            return -1;
        }
        if(arr[mid] == value){
            return mid;
        }else if(arr[mid] > value){
            return binarySearch(arr,left,mid-1,value);//二分查找需要找到对应的下标返回，查找到的就返回
        }else{
            return binarySearch(arr,mid+1,right,value);
        }
        //语法上的else-if也会受影响
    }
    public static ArrayList<Integer> binarySearch(int [] arr, int left, int right, int value,boolean flag){
    //二分查找的优化就是，它找到一个目的value之后递归就不往下进行了，如果这样的情况，其实不会出现重复的情况，也就是说
        //不是我想的那样用ArrayList会重复元素添加
        //ArrayList就是动态数组，不是集合，可以存在相同的，是列表
        int mid = (left+right)/2;
        //其实left和right相等的时候就是也可以用最下面的判断条件顺便判断，所以退出条件可以更优化
        //递归的思路可以是直接通过第1次的递归的思路来完成，也就是直接通过显示的代码的逻辑去理解
        if(left > right){
            return new ArrayList<Integer>();
        }
        if(arr[mid] == value){
            ArrayList<Integer> resIndex = new ArrayList<Integer>();
            int temp = mid-1;
            while( true){
                if(temp <0 || arr[temp]!=arr[mid]){
                    break;
                }
                resIndex.add(temp);
                temp--;
            }
            temp = mid+1;
            while( true){
                if(temp > arr.length-1 || arr[temp]!=arr[mid]){
                    break;
                }
                resIndex.add(temp);
                temp++;
            }
            return resIndex;
        }else if(arr[mid] > value){
            return binarySearch(arr,left,mid-1,value,true);//二分查找需要找到对应的下标返回，查找到的就返回
        }else{
            return binarySearch(arr,mid+1,right,value,true);
        }
    }
}
//二分查找的非递归语句就是再while循环里面更改mid的值，然后再循环到上面，而且在最上面有每次需要改变的就是放在上面的代码
//一般是通过模拟栈来实现递归转循环
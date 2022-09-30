package basic.searchalgorithm;

public class InsertSearch {
    public static void main(String[] args){
        int[] arr = {2,2,2,3,11,42,67,100000,19999999};
        System.out.println(""+insertSearch(arr,0,arr.length-1,0));
    }
    //这个函数的意思就是在对应的区间寻找需要遇到的索引
    //所以在整体没有找到就可以在左半部分或者右半部分找需要的索引
    //这种二分问题都会把中间的排除掉，在二分查找中是这样的，在快排中的二分思想，分治思想也是把中间的排除掉
    //也就是最后的就是判断语句if-else，l++,r--就是做这项工作
    public static int insertSearch(int [] arr, int left, int right, int value){
        int mid = left + (right-left)*(value - arr[left])/(arr[right]-arr[left]);
        if(left > right || value<arr[0] || value > arr[arr.length-1]){
            //只判断一次定义就好了，因为第二次进递归的时候肯定是满足最大的那个条件之一，另外一个通过下面的限制也满足了
            return -1;
        }
        if(arr[mid] == value){
            return mid;
        }else if(arr[mid] > value){
            return insertSearch(arr,left,mid-1,value);//二分查找需要找到对应的下标返回，查找到的就返回
        }else{
            return insertSearch(arr,mid+1,right,value);
        }
        //语法上的else-if也会受影响
    }
}

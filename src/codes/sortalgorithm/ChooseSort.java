package codes.sortalgorithm;

import java.util.Arrays;

public class ChooseSort {
    //选择排序的思路，从整个数组中选择最大的，或者最小的和arr[0]交换
    //之后从后1~n-1选出最大或者最小的再次交换arr[1]，都是同理,之后再和arr[2]交换
    //直到全部排好，因此也是两层循环，
    //外层循环决定几轮排序，每次排序确定一个最大值或者最小值，外层循环(数组长度-1)次，
    //内层循环就是再后面，也就是已经排好的个数后面遍历，最终循环(数组长度-1-轮数)次，和冒泡排序一样

    public static void main(String[] args){
        int[] arr = new int [8];
        for(int i = 0;i<8;i++){
            arr[i] = (int)(Math.random()*80000);
        }
        long startTime = System.currentTimeMillis();
        for(int i=0;i < arr.length-1;i++){//i表示第几轮循环，后面就是总循环次数
            int k=i;//记录开始的索引
            for(int j=i ; j<arr.length;j++){//从第n+1索引开始找到后面数组最小或者最大的数
                if(arr[j]<arr[k]){//不能从加一开始那就乱套了，加一开始是插入排序，选择排序是从整体选择一个最大的
                    k=j;
                }
            }
            //最终得到最小值的索引
            //应当是一次内循环之后会得到一个最值
            int temp = arr[i];//arr[i]表示要确定的最大最小值放的位置
            arr[i] = arr[k];
            arr[k] = temp;
        }
        long endTime = System.currentTimeMillis();
        System.out.println(""+(endTime-startTime));
        System.out.println(""+Arrays.toString(arr));
    }
}
//韩老师讲的思路是在于把待交换的也就是arr[i]假定为一个最小或最大的，跳入一个循环然后和后面的比较
//直到找到一个更小或更大的再赋值最后决定是否要交换，这样的思路在一定程度向最小化了代码量，是比较好的思路

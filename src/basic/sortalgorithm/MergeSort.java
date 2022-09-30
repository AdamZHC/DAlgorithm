package basic.sortalgorithm;

import java.util.Arrays;

public class MergeSort {
    public static void main(String []  args){
        int[] arr = {1,3,4,5,2,6,7,8};
        int temp[] = new int[8];
        System.out.println(""+ Arrays.toString(arr));
    }
    public static void mergesort(int arr[],int left,int right, int[] temp){
        if(left < right){
            int mid = (left+right)/2;
            //左递归
            mergesort(arr,left,mid,temp);
            //右递归
            mergesort(arr,mid+1,right, temp);
            //以上是展开，下面是合并
            //合并
//            先递归后合并，理解了理解了，就是有递有归
            //其实理解起来就是那个树形结构，先展开变成完全树，然后再一个一个地合并（但是其实是有顺序地合并的）
            merge(arr,left,mid,right,temp);

        }
    }
    public static void merge(int[] arr, int left, int mid, int right, int[] temp){
        //传入对应数组，传入对应的左部分，右部分的索引还有一个中间缓存数组（注意到传引用的时候都是，传入对应的本来数组和要处理的索引）
        //就是用到了合并链表的思想，哪个更大，就选哪个
        int i=left;
        int j= mid+1;
        int t = 0;
        //合并链表到一个新的链表中
        while(i <= mid || j <= right){
            if(arr[i]>=arr[j]){
                temp[t] = arr[i];
                i++;
            }else{
                temp[t] = arr[j];
                j++;
            }
            t++;
        }
        //最后退出来的时候可能回有剩余
        while(i <= mid ){
            temp[t] = arr[i];
            t++;
            i++;
        }
        while(j <= right ){
            temp[t] = arr[j];
            t++;
            j++;
        }
        //之后就是再返回到原数组中
        t=0;
        int tempLeft = left;//这样left还能留下来
        while(tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}

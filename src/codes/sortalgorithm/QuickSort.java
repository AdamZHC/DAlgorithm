package codes.sortalgorithm;

public class QuickSort {
    //快速排序的思路就是分治法的思路，在一个数组中任意找一个数，然后大于这个数的放在一边，小于这个数的放在另一边
    //然后递归把每次的分好的两个数组都按这个思路再进行快速排序，然后就可以了，但是相对来说，代码思路中的细节问题
    //比较多，所以处理起来也比较麻烦
    public static void main(String[] args){
        int[] arr = new int [80000];
        for(int i = 0;i<80000;i++){
            arr[i] = (int)(Math.random()*800000);
        }
        long startTime = System.currentTimeMillis();
        quicksort(arr,0,arr.length-1);
        long endTime = System.currentTimeMillis();
        System.out.println(""+(endTime-startTime));
    }
    public static void quicksort(int[] arr,int left, int right){//这里表示每次交换的范围，也就是每一次找到二分位置
        //以及左右重新排序的左和右left right
        int l = left;
        int r = right;
        int pivot = arr[(left + right)/2];
        int temp;
        //这个快速排序和二分查找又有点不一样
        while(r>l){
            //找到每一边各自从开始比pivot大的或者小的
            while(arr[r]>pivot){
                r--;
            }
            while(arr[l]<pivot){
                l++;
            }
            //如果完全两边已经排列好的话，那就退出
            if(r <= l){
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //这就是如果可以找到若干个中值则需要采用下面的退出方法
            //如果中间有多个000000这样很多的话，不好退出会没有必要地增加语句，因此最好加上该语句
            if(arr[r] == pivot){
                r--;
            }
            if(arr[l] == pivot){
                l++;
            }
        }
        //这个判断语句的目的就是把已经排好的中间数排除掉
        if(l == r){
            r--;
            l++;
        }
        //下一步递归，注意此时是左和之前定义的右比较，右和之前定义的左比较，这里是不同的
        if(left < r){
            quicksort(arr,left,r);
        }
        if(right > l){
            quicksort(arr,l,right);
        }
    }
}

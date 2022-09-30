package review;

public class MergeSort {

    public static void main(String[] args) {

    }
    //二路归并
    //归并归并——先递归后合并
    //这个函数意思就是排好序了
    public void sort(int[] arr, int start, int end) {
        int mid = start + (end - start - 1) / 2;
        //一般递归条件要在前面处理好
        //所以下面的意思就是指已经排好序了
        if(start < mid){
            sort(arr, start, mid);
        }
        if(mid + 1 < end) {
            sort(arr, mid + 1, end);
        }
        //合并的思想就是来自于之前的过程
        merge(arr, start, mid, mid + 1, end);

    }
    public void merge(int[] arr, int LStart ,int LEnd, int RStart, int REnd) {
        int[] temp = new int[REnd - LStart + 1];
        int Lp = LStart;
        int Rp = RStart;
        int p = 0;
        //分析过后发现只能这么写
        while(p <= REnd && Lp <= LEnd && Rp <= REnd){
            if(arr[Lp] < arr[Rp]) {
                temp[p++] = arr[Lp++];
            }else{
                temp[p++] = arr[Rp++];
            }
        }
        if(p < REnd + 1){
            if(Lp == LEnd)
                for(int i = Rp; i <= REnd; ++i)
                    temp[p++] = arr[i];
            else
                for(int i = Lp; i <= LEnd; ++i)
                    temp[p++] = arr[i];
        }

        for(int i = 0;i <= REnd; ++i) {
            arr[i] = temp[i];
        }
    }

}

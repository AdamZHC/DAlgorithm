package review;

public class QuickSort {
    public static int partition(int[] arr, int start, int end){
        int num = arr[start];
        int i = start + 1;
        int j = end;
        while(i < j) {
            //此时不关注是否相等
            //由第一个循环决定是从哪边交换，而且有一定的对应性
            /**
             * 如果中间只有比num大的，因为本身第一个循环就可以运行下去，会跑到i的地方——是小于num的
             * 如果中间只有比num小的，第二个循环会一直运行下去——小于num的
             * 如果都有的话那就重复之前的语句
             * 所以从哪里开始选择就决定了后面的大于小于1
             */
            while(i < j && arr[j] > num) --j;
            while(i < j && arr[i] < num) ++i;
            if(j == i){
                //如果是num的话，只交换num上的值，没有意义，应当需要交换arr上的值
                arr[start] = arr[start] ^ arr[i];
                arr[i] = arr[start] ^ arr[i];
                arr[start] = arr[start] ^ arr[i];
                break;
            }else{
                arr[j] = arr[j] ^ arr[i];
                arr[i] = arr[j] ^ arr[i];
                arr[j] = arr[j] ^ arr[i];
            }
        }
        return i;
    }
}

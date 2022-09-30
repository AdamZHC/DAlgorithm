package review;

public class ShellSort {
    //希尔排序
    public void sort (int[] arr) {
        int len = arr.length;
        //step是步长， 在逐步的变化过程中
        int step = len;
        while(step >= 1) {
            //步长——同时代表这这一组有多少个数
            step /= 2;
            //共有几组
            int num = len / step;
            //开始插入排序
            for(int i = 0; i < num; ++i) {
                //i表示组序数
                for(int j = step + i; j < len; j+= step) {
                    int e = arr[j];
                    for(int k = j - step; k - step >= 0; k -= step) {
                        if(e > arr[k - step]) {
                            arr[k] = e;
                            break;
                        }
                        arr[k] = arr[k - step];
                    }
                }
            }
        }
    }
}

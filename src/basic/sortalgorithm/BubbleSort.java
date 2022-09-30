package basic.sortalgorithm;


public class BubbleSort {
    //最外层的循环决定交换几趟，次数是(数组个数-1)次数
    //内层循环决定在一趟中比较几次，次数(是数组个数-1-趟数)，因为每一趟都会确定一个最大的个数，下一次会减少
    //优化方法的思路就是如果在一趟交换中都没发生变化的话,那么就不需要下次交换设置一个flag是否没有交换
    public static void main(String[] args){
        //静态方法不允许调用非静态变量

        int[] arr = new int [80000];
        for(int i = 0;i<80000;i++){
            arr[i] = (int)(Math.random()*80000);
        }
        //外面的循环
        long startTime = System.currentTimeMillis();
        for(int i =0;i <arr.length-1;i++){//每趟循环确定一个对应序列的最值，最大值，第二最大值，第三最大值等等
            //内部的循环
            //设置一个旗标，标志其在这一躺遍历中是否交换，初始设为false
            //如果有交换说明还未排好序 置为true
            boolean flag = false;
            for(int j=0;j<arr.length-i-1;j++){
                if(arr[j]>arr[j+1]){
                    swap(arr,j);
                    flag = true;
                }
            }
            if(flag == false){
                break;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println(""+(endTime-startTime));

    }
    public static void swap(int[] arr,int k){
        int a = arr[k];
        arr[k] = arr[k+1];
        arr[k+1] = a;
        return;
    }
}

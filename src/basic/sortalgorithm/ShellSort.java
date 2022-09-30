package basic.sortalgorithm;

public class ShellSort {
    public static void main(String [] args){
        int[] arr = new int [80000];
        for(int i = 0;i<80000;i++){
            arr[i] = (int)(Math.random()*800000);
        }
        long startTime = System.currentTimeMillis();
        //决定何时结束循环，循环几次
        for(int gap = arr.length/2; gap> 0;gap/=2){
            //从步长的后半部分算起，后面的每对应到前面的数字都需要交换
            //这就是分组后的组内开始交换
            //分组内的排序开始排序
            //其实就是一个不同步长的冒泡排序
            //因为要把每个分组的内部都排序到从大到小和从小到大，与冒泡排序的区别就是步长不同
            //注意观察下面的代码与冒泡排序是类似的，只不过是从正向冒泡
            //不同于选择排序的选择到最值排到前面的位置，和冒泡排序和插入排序是一样的都有[j+1]与[j]这样的字眼
            //移位式
            for(int i =gap; i<arr.length;i++) {//在这里就是不同步长的插入排序
                //这里i++的原因就是，虽然是有不同步长的，但是还需要每一分组都要比较，因此就需要i++每一组比较
                int insertVal = arr[i];
                int insertIndex = i - gap;
                while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                    arr[insertIndex + gap] = arr[insertIndex];
                    insertIndex -= gap;
                }
            }
            //交换式
//            for(int i=gap; i<arr.length; i++){
//                for(int j = i-gap; j>=0;j-=gap){
//                    if(arr[j]>arr[j+gap]){
//                        int temp = arr[j];
//                        arr[j] = arr[j+gap];
//                        arr[j+gap] = temp;
//                    }
//                }
//            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println(""+(endTime-startTime));
    }
}
//显然不是有序的，那个人说的有问题，每一次希尔循环之后并不是有序的，只是分散得更集中了，而且即算是1之前的那个数也并不是百分百排好序的

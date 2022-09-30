package basic.sortalgorithm;

public class InsertSort {
    //思路就是把待排序的表看作是一个有序表和一个无序表，有序表的初始长度为1，无序表的长度为n-1
    //每次从无序表中选取一个元素插入到有序表中，直接找到有序表的应该插入的位置，最终结束
    //很好的理解就是打麻将，另外，插入的方式就是从后面开始查找，直到找到可以插入的位置
    //首先最外层排序决定从第几个开始，然后循环次数是n-1次
    public static void main(String[] args){
        int[] arr = new int [80000];
        for(int i = 0;i<80000;i++){
            arr[i] = (int)(Math.random()*800000);
        }
        long startTime = System.currentTimeMillis();
        for(int i =1; i<arr.length;i++){
//            int insertVal = arr[i];
//            int insertIndex = i-1;
//            while(insertIndex >= 0&& insertVal <arr[insertIndex]){
//                arr[insertIndex+1] = arr[insertIndex];
//                insertIndex--;
//            }-- 没有交换的过程，一直都是后者赋给前者值，会出现两个相同的值，知道找到目的值
            //注意看演示就是一直前者赋给后者值，不存在交换的语句，一直把待插入的语句保留着
            for(int j=i-1 ; j >=0 ;j-- ){
                //如果要一个一个比较的话，基本上都是j和j+1比较，典型的冒泡排序和插入排序
                //典型一个注意一个优化（也不能叫优化）就是说如果在最后的那个数，直接就比待插入的那个数有对应关系的还就不需要比较后面的
                //因为即使不跳入判断每次都会j++也会算一个语句,所以还是速度比较慢,原来硬件不行也会很影响
                if(arr[j+1] <= arr[j]){
                    break;
                }
//                if(arr[j] >= arr[0]){
//                    int temp = arr[j+1];
//                    arr[j+1] = arr[j];
//                    arr[j] = temp;
//                    break;
//                }
                if(arr[j+1] > arr[j]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println(""+(endTime-startTime));
    }
}
//插入排序韩老师的思想应当是
//其实插入排序的思想是和我是一致的也就是分两组找到最小的来插入
//但是实现的方式不一样，思想是一致的
//问题在于：因为数组不同于链表找到可以插入的位置不能直接插入，只能一个一个往后移
//而我的解决方法就是类似冒泡排序，每一次都交换直到交换得到合适位置，但是韩老师的
//解决方法就是直接先移动最后交换，实质上是一样的，我的语句在于每次都需要定义变量
//来交换但是韩老师的语句则不需要，一直都是为真正插入时候往后移而一直准备，也就是
//每次都交换，把待插入的值先保持住，后面的一个一个往前移直到可以插入为止，实际上没啥区别
//也就是说，那个插入排序也就是得一个一个移动，显然不类似于冒泡排序，
//最好还是用韩老师那样的实现一下，也就是保存一个变量，往前移动----这也可以算是一个核心思想

package basic.tree.binaryindexedtree;

import java.util.Arrays;
//这里的思想也是数组中索引转化到树状数组中+1的索引去处理问题，得到树状数组中的索引之后，把后者拆分成2的幂和，
//之后再进行处理对应的幂和然后再去对应树状数组中的索引，也就是二进制的1位1位去掉1，
//先考虑整个二进制加和，低位去掉一位1得到的数对应到树状数组中的索引加和，循环操作，注意是依次干掉1，而不是一位一位去掉，而是每次去掉，得到
//的数不是减一，而是保持位数不变，去掉一，而实现的函数就是lowBit
//BIT数组是往后移动的新数组，长度是length+1,视频里面讲的是统计数据而不是实际的数据
public class LowBitDemo {
    public static void main(String[] args){
        int arr[] =  {1,1,1,1,1,1,1,1};
        for(int i = 1; i <= arr.length; i ++){
            System.out.printf("lowBit( %d ) = %d\n",i,lowBit(i));
        }
        //由于前一个减后一个不一定能找到正确的，所以会需要+1-1
        for(int i = 0 ; i < arr.length ; i++){
            for(int j = i+ lowBit(i+1); j < arr.length; j += lowBit(j+1)){
                arr[j] += arr[j - lowBit(j)];
            }
        }
        System.out.println(Arrays.toString(arr));
    }
    public static int lowBit(int n){
        return  n & ( - n );
    }
}
//生成一个二叉索引树

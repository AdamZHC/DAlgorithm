package basic.sortalgorithm;

public class RadixSort {
    public static void radixSort(int [] arr){
        int[][] bucket = new int [10][arr.length];
        int bucketElementCounts[] = new int[10];
        int maxSize = 4;
        int index=0;
        for(int k = 0; k<maxSize;k++){
            for(int i = 0; i<arr.length ; i++){
                //取出每个元素的对应位数
                int digitOfElement = arr[i]/ (int)Math.pow(10,maxSize) % 10;
                bucket[digitOfElement] [bucketElementCounts[digitOfElement]] = digitOfElement;
                bucketElementCounts[digitOfElement]++;
            }
            index = 0;
            for(int j = 0;j<bucketElementCounts.length;j++){
                if(bucketElementCounts[j] != 0){
                    for(int l =0;l<bucketElementCounts[j];l++){
                        arr[index++] = bucket[j][l];
                    }
                }
            }
        }

    }
}

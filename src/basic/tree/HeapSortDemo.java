package basic.tree;

public class HeapSortDemo {
    public static void main(String[] args){
        int[] arr = new int [80000];
        for(int i = 0;i<80000;i++){
            arr[i] = (int)(Math.random()*800000);
        }
        long startTime = System.currentTimeMillis();
//        int[] arr = {2,33,4,333,223,32,34,23,};
        HeapSort(arr);
        //System.out.println(Arrays.toString(arr));
        long endTime = System.currentTimeMillis();
        System.out.println(""+(endTime-startTime));
    }
    public static void HeapSort(int[] arr){
        for(int i =0 ; i<arr.length-1;i++){
            consHeap(arr,0,arr.length-1-i);
            int temp = arr[0];
            arr[0] = arr[arr.length-1-i];
            arr[arr.length-1-i] =temp;
        }
    }
    //思路即是构造大顶堆或者小顶堆，最后把最大的和最后一个交换，最后再重新整理成大顶堆或者小顶堆
    //构造大顶堆 -- 这里使用一个递归函数
    public static void consHeap(int[] arr, int root, int leaf) {//因为是对树进行顶化，所以一直到要到叶子结点
        //但是此时不会涉及到其他的树，因为有那个条件，不会使得索引到其他的树 ---> n->2*n+1 and 2*n+2
        //因为是递归函数，所以要考虑递归结束的条件 -- 就是当遍历到一个叶子结点的时候就是可以结束了
        //我认为叶子结点应当就是当其索引超出数组的范围时候
        if (root * 2 + 1 > leaf) {
            return;
        }
        //接下来是核心代码
        //注意到他说是自下向上，所以应该是先到最低端
        //考虑一叶子结点的情况,必须从最下面开始遍历，不然只会一叶障目
        int temp = arr[root];
        //首先递归注意事项 1.递归条件的退出
        //              2.有递有归，考虑如果在一开始递的过程中就解决问题会不会一叶障目
        //              3.考虑逻辑问题，就是说这个递归函数总能解决一些问题，在函数体内，如果根据逻辑使得某几个部分完成
        //递归，这可以看作是任何部分完成的递归，然后根据逻辑完成任务 -- 比如说汉诺塔问题，完成的就是一次汉诺塔移动，可以
        //推及总概的说完成n-1的汉诺塔移动，只需要最后第n次就可以了，也就是说这样的问题实现，完全可以是根据逻辑在函数体内判断
        //分析，完成递归的那部分任务后再去考虑实现
        consHeap(arr,root*2+1,leaf);
        consHeap(arr,root*2+2,leaf);
        if(root * 2 + 1 == leaf){//说明是单叶子结点的情况，而且这种情况只会在最后出现，所以判断完之后不需要对于下面的进行大根堆化
            arr[root] = temp > arr[root * 2 + 1] ? temp : arr[root * 2 + 1];
            arr[root * 2 + 1] = temp < arr[root * 2 + 1] ? temp : arr[root * 2 + 1];
            return;
        }
        if (arr[root] >= arr[root * 2 + 1] && arr[root] >= arr[root * 2 + 2]) {
            return;
        }
        while (arr[root * 2 + 1] >= arr[root] && arr[root * 2 + 1] > arr[root * 2 + 2]) {
            arr[root] = arr[root * 2 + 1];
            arr[root * 2 + 1] = temp;
            consHeap(arr, root * 2 + 1, leaf);
        }
        while (arr[root * 2 + 2] >= arr[root] && arr[root * 2 + 2] > arr[root * 2 + 1]) {
            arr[root] = arr[root * 2 + 2];
            arr[root * 2 + 2] = temp;
            consHeap(arr, root * 2 + 2, leaf);
        }
        return;
        //还是有递有归，思想还需要再掌握
        //堆排序的前一部分的遍历就是涉及到一个指向偏转，找到最大的指针，最后在细节的部分进行交换
        //两次非递归调整堆的大顶化和小顶化，自下而上
        //堆排序的核心代码应当是这样的思路：
        //1.首先从最后一个非叶子结点开始完成伪根堆化(这里面由于是从下开始的，所以可以直接结束break)，完成根节点的伪根堆化
        //之后，这样就实现了一个树的根堆化，
        //2.之后的调整每从根节点的伪根堆化都可以实现真正的根堆化
        //
    }
}

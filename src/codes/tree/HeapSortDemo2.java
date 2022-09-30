package codes.tree;

public class HeapSortDemo2 {
    //添加的时候每次从插入数的父节点处进行根堆化，注意此时一直到根堆到根节点，注意如果是一个一个创建的话，那就不需要
    //进行上下的双层递归，直接一直往上走就好了，不需要到一个父节点再往下走，只需要把这一层的搞好就可以了
    //这时的根堆化比较多就是，父节点递归根堆之后，父节点的父节点还要递归根堆化而且是要包括下面的，这样才能保证正确的构造根堆
    //交换之后从根节点之后进行一次递归根堆化
    public static void FakeHeapSort(int[] arr,int i){//完成伪根堆化
        int temp = arr[i];
        for (int k = i * 2 + 1; k < arr.length - 1; k = k * 2 + 1) {//这里的length意思就是可以等于length-1
            if (k + 1 < arr.length && arr[k + 1] > arr[k]) {//这里是判断左右结点，找到字结点最大的
                k++;
            }//就是找到一个指向来确定最大的位置，也就只是完成了眼前的转换，能不能把最大的移到根节点是不一定的(没有之前的处理)
            if (arr[k] > temp) {
                arr[i] = arr[k];//这里就是说要把最大的值换到根结点上来 -- 如果不对下面做处理，不会是最大的值
                i = k;//为最下一步做准备，可以注意到这一步对原来的并不起什么作用，其实就是交换找到把最大的移上去，原来的那个
                //本来在根节点的位置的值就是找个地方插入，也就是在这里
            } else {
                break;//可以break的原因就是从最小的地方开始的
            }
        }
        arr[i] = temp;//这一步其实就是为了把位置交换也就是，本来在根节点的位置的值就是找个地方插入，也就是在这里
    }
    public static void HeapSort(int[] arr){
        for(int i = arr.length/2-1;i>=0;i--){
            FakeHeapSort(arr,i);//观察i的值，这里是从下至上的
        }//完成这一套就完成了根堆化
        //然后就是交换
        int temp = 0;
        for(int j = arr.length-1;j>0;j--){
            temp = arr[0];//每次都要更新结点处的值，所以这条语句放在里面
            arr[0] = arr[j];
            arr[j] = temp;
            FakeHeapSort(arr,0);//再次进行为伪根堆化，这样可以实现再一次的根堆化
        }
    }
}

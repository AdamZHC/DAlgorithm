package review;

import java.util.*;
import java.math.BigInteger;

public class HeapSort {

    static void sort(int[] tree) {
        int n = tree.length;
        initHeap(tree);
        for(int i = n - 1; i > 0; --i) {
            tree[0] =  tree[0] ^ tree[i];
            tree[i] =  tree[0] ^ tree[i];
            tree[0] =  tree[0] ^ tree[i];
            heapFormedR(tree, 0, i);
        }
    }
    //根堆化
    //考虑:以当前结点为根节点使得下面的所有结点进行根堆化
    static void initHeap(int[] tree) {
        int n = tree.length;
        //从第一个分支节点开始
        for(int i = n / 2 - 1; i >= 0; --i) {
            heapFormedR(tree, i, n);
        }
    }
    /**
     * 递归实现
     * @param tree:对应的完全二叉树数组
     * @param i:对应的坐标
     */
    static void heapFormedR(int[] tree, int i, int n){
        //因为下面保持了一定根堆的性质，所以不需要完全根堆化
        //注意堆排序是选择排序
        //此时我就不需要关注另外一个点
        //对于完全二叉树来说的叶节
        if(i > n / 2 - 1) return;
        int swapIdx = i * 2 + 2 > n - 1 ? i * 2 + 1 : (tree[i * 2 + 1] > tree[i * 2 + 2] ? i * 2 + 1 : i * 2 + 2);
        if(tree[i] < tree[swapIdx]){
            //异或交换
            tree[i] = tree[swapIdx] ^ tree[i];
            tree[swapIdx] = tree[swapIdx] ^ tree[i];
            tree[i] = tree[swapIdx] ^ tree[i];
            heapFormedR(tree, swapIdx, n);
        }
    }

    /**
     * 非递归实现
     * @param tree:对应的完全二叉树数组
     * @param i:对应的坐标
     */
    static void heapFormedC(int[] tree, int i, int n) {
        int tIdx = i;
        while(tIdx <= n / 2 + 1){
            int swapIdx = tIdx * 2 + 2 > n - 1 ? tIdx * 2 + 2 : (tree[tIdx * 2 + 1] > tree[tIdx * 2 + 2] ? tIdx * 2 + 1 : tIdx * 2 + 2);
            if(tree[tIdx] < tree[swapIdx]){
                tree[tIdx] = tree[swapIdx] ^ tree[tIdx];
                tree[swapIdx] = tree[swapIdx] ^ tree[tIdx];
                tree[tIdx] = tree[swapIdx] ^ tree[tIdx];
            } else break;
            //以上的break是指如果大于的话，那就退出，因为下面就都是完全根堆了，没必要遍历了
            tIdx = swapIdx;
        }
    }
}

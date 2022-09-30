package basic.pointoffer;


import java.util.Arrays;

public class BinaryTree {
    public static void main(String[] args) {
        int[] F = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] M = {4, 7, 2, 1, 5, 3, 8, 6};
        int[] ans = new int[16];
        reBuildBinaryTree(F,0,7, M ,0,7,0, ans);
        System.out.println(Arrays.toString(ans));
    }
    /**
     * 重建二叉树
     * @param F:前序序列
     * @param fStart:前序序列的上界
     * @param fEnd:前序序列的下界
     * @param M:中序序列
     * @param mStart:中序序列的上界
     * @param mEnd:中序序列的下界
     * @param index:结果树的索引
     * @param ans:对应的结果树
     */
    public static void reBuildBinaryTree(int[] F, int fStart, int fEnd, int[] M, int mStart, int mEnd, int index, int[] ans){
        if(fStart >= fEnd){
            ans[index] = F[fStart];
            return;
        }
        if(mStart >= mEnd){
            ans[index] = F[mStart];
            return;
        }
        //初始化根结点
        //这里使用索引会更好
        int root  = F[fStart];
        int fRoot = fStart;
        int mRoot = 0;
        //找到对应的根结点位置
        //常见技巧:二分 + 左右扫描
        //直接分治的情况是二分查找无法处理的，二分只能处理减治的情况，分治的情况只能是递归，就比如说二叉树的遍历
        int mid = (mStart + mEnd) / 2;
        boolean flag = false;
        //主要是要找到根结点的位置，也就是root才能所以要把i赋给对应的mRoot根结点
        for(int i = mid; i <= mEnd; i ++){
            if ( M[i] == root) {
                mRoot = i;
                flag = true;
                break;
            }
        }
        for(int i = mid; i >= mStart && !flag ; i --){
            if(M[i] == root){
                mRoot = i;
                break;
            }
        }
        ans[index] = root;
        //重建左子树
        //寻找在前序序列中的左子树的上下界(注意这里不需要直接可以确定长度就可以)之后可以获取左子树的长度
        //问题不大，如果按之前思考的话是没有问题的
        int len = mRoot - mStart;
        //关于树的运算一定要注意，前面一定要有判断条件
        if( fStart + 1 <= fStart + len && mStart <= mRoot - 1){
            reBuildBinaryTree(F,fStart + 1 ,fStart + len, M, mStart, mRoot - 1, index * 2 + 1, ans);
        }
        if(fStart + len + 1 <= fEnd + len &&  mRoot + 1 <= mEnd) {
            reBuildBinaryTree(F, fStart + len + 1, fEnd, M, mRoot + 1, mEnd, index * 2 + 2, ans);
        }
    }

}

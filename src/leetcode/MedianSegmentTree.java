package leetcode;

import java.util.Arrays;

class MedianSegmentTree {
   Node[] tree;
   public MedianSegmentTree(int[] arr){
       int n = arr.length;
       tree = new Node[n << 2];
       buildTree(arr, 0, n - 1, 0);
   }

   //线段树递归建树
   void buildTree(int[] arr, int L, int R, int idx){
       if(L == R){
           tree[idx] = new Node(new int[]{arr[L], arr[L]}, L, L);
           return;
       }

       int M = (L + R) >> 1;

       buildTree(arr, L, M, ln(idx));
       buildTree(arr, M + 1, R, rn(idx));

       //递归建树首先要注意和C++的结构体直接分配内存不同，这里是线段树的创建
       //一定要注意对应的new分配内存
       int[] m1 =  tree[ln(idx)].median;
       int[] m2 =  tree[rn(idx)].median;
       int[] median = getMedian(m1, m2);
       tree[idx] = new Node(new int[]{median[0], median[1]}, L, R);
   }

   double getAns(){
       return (tree[0].median[0] + tree[0].median[1]) / 2.0;
   }
   //修改对应的线段树
    void update(int I, int val, int idx){
       if(tree[idx].L == I){
           tree[idx].median = new int[]{val, val};
           return;
       }
        //后来的M就是获取对应tree结点的中值
       int M = M(idx);
       if(I <= M){
           update(I, val, ln(idx));
       }else{
           update(I, val, rn(idx));
       }
    }

   //获取对应的中位数数组
   int[] getMedian(int[] m1, int[] m2){
       int[] arr = new int[4];
       arr[0] = m1[0]; arr[1] = m1[1];
       arr[2] = m2[0]; arr[3] = m2[1];
       Arrays.sort(arr);
       return new int[]{arr[1], arr[2]};
   }
    //内联函数获取左儿子和右儿子
    int ln (int idx){
        return (idx << 1) + 1;
    }
    int rn (int idx){
        return (idx << 2) + 2;
    }
    int M(int idx){
       return (tree[idx].L + tree[idx].R) >> 1;
    }

    void detail(){
       for(Node n : tree)
           if(n != null)
               n.detail();
    }
}

class Node{
    int[] median;
    int L;
    int R;
    public Node(int[] median, int L, int R){
        this.median = median;
        this.L = L;
        this.R = R;
    }

    void detail(){
        System.out.printf("[%d, %d]:[%d, %d]\n",L, R, median[0], median[1]);
    }
}

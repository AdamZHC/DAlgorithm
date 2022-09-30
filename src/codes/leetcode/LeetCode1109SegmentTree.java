package codes.leetcode;


//注意left和right由于传进去的就是对应的下标所以，就是对应的叶子结点，也就是原对应数组的下标
public class LeetCode1109SegmentTree {
    int [] target;
    int count = 0;
    public int[] corpFlightBookings(int[][] bookings, int n) {
        //初始化线段树
        int [] tree = new int[ 4 * n  + 5];
        target  = new int[n];
        for( int[] booking : bookings){
            update(tree , 0 , 0 , n-1 , booking[0]-1 , booking[1]-1 ,booking[2]);
        }
        //深搜也可以按顺序搜到但是广搜搜不到
        return target;
    }
    //知道到左右边界，不知道第几个结点也不行
    //所以这样的话空间复杂度就上来了，左右边界得知道，第几个结点得知道
    //如果使用类的话，那就不需要了，每次就是很方便
    //查询不需要加,但是修改必须得加
    //小技巧，传数组传索引
    public void update(int[]tree, int i ,int left, int right, int L, int R, int value){
        if(left == right){
            tree[i] += value;
            target[left] += value;
            return;
        }
        if( R <= (left + right) / 2 ){
            update(tree , i * 2 + 1 , left , (left + right) / 2 , L , R , value);
        }else if( L >= (left + right) / 2 + 1){
            update(tree , i * 2 + 2 , (left + right) / 2 + 1, right , L , R ,value);
        }else{
            update(tree , i * 2 + 1, left , (left + right) / 2 , L , (left + right) / 2, value);
            update(tree , i * 2 + 2,(left + right) / 2 + 1 , right , (left + right) / 2 + 1, R ,value);
        }
        tree[i] = tree[2 * i + 1] + tree[ 2 * i + 2];
    }
}
//声明线段树
//问题1:怎么组织每个结构体就是一个问题了 -- (树或者数组) -- 访问的时候不同
//问题2:怎么实现操作，创建，区间修改
//本身的数组是不需要存具体由左右结点，当然也可以
//视频里讲的就是最原生的实现，left和right也可以编写实现
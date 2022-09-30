package basic.recursion;

public class Queen8 {
    //有回溯的思想就是需要一步一步放置每一个棋子，如果
    //棋子放置不匹配的话也就是放向下一列
    int max = 8;
    int [] arr = new int[8];
    //不同的数字表示摆放位置不同
    public void check(int n){
        //这个方法内部就是实际的递归函数
        if(n==8){
            return;
        }
        //放入不同的列，也就是一个一个放置
        //回溯的重点在于for循环
        //把每一个皇后都是一个位置一个位置，一列一列地放置
        for(int i =0;i<max;i++){
            //把第n个皇后放到第i列上
            arr[n] = i;
            //如果成功的话，继续放置
            if(judgement(n)){
                check(n+1);
            }
        }
    }
    public boolean judgement(int n){
        //判断前n个皇后是否与其位置有重突，一个一个判断
        //个人认为这个判断条件很关键
        for(int i =0; i<n;i++){
            if(arr[i] == arr[n] || Math.abs(n-i) == Math.abs(arr[n]-arr[i])){
                //这个摆放位置有一个冲突说明在这个位置就不可以直接返回false
                return false;
            }
        }
        //都遍历结束了这次才说明可以了
        return true;
    }
}


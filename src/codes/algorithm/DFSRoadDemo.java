package codes.algorithm;

public class DFSRoadDemo {//prim就是最小生成树
    static int[][] matrix = new int[7][7];
    public static void main(String[] args){
        //A-0,B-1,C-2,D-3,E-4,F-5,G-6
        matrix[0][1] = 5;matrix[0][2] = 7;matrix[0][6] = 2;//A
        matrix[1][0] = 5;matrix[1][6] = 3;matrix[1][3] = 9;//B
        matrix[2][0] = 7;matrix[2][4] = 5;//C
        matrix[3][1] = 9;matrix[3][5] = 4;//D
        matrix[4][2] = 8;matrix[4][6] = 4;matrix[4][5] = 5;//E
        matrix[5][3] = 4;matrix[5][6] = 6;matrix[5][4] = 5;//F
        matrix[6][4] = 4;matrix[6][5] = 8;matrix[6][0] = 2;matrix[6][1] = 3;//G
        dfs(0,0);
    }
    static Stack stack = new Stack(8);//用来存结果
    static boolean[] isVisited = new boolean[7];//用来看是否访问过，初值为false
    public static void dfs(int step,int num){//我喜欢用栈存
        //跳出循环
        if(stack.top == 6){
            int sum = 0;
            for(int i=0 ;i<7;i++ ){
                System.out.print(stack.arr[i]);
                sum +=  i == 6 ? 0 : matrix[stack.arr[i]][stack.arr[i+1]];
            }
            System.out.println();
            System.out.println(sum);
            return;
        }
        //寻找逻辑关系
        for(int i = 0; i < 7 ; i++){
            if(matrix[num][i] != 0 && isVisited[i] != true){
                isVisited[i] = true;
                stack.push(i);
                dfs(step+1,i);
                stack.pop();
                isVisited[i] = false;
            }
        }
    }
}
//class Stack{
//    int top = -1;
//    int[] arr;
//    public Stack(int capacity){
//        arr = new int[capacity];
//    }
//    public void push(int num){
//        arr[++top] = num;
//    }
//    public int pop(){
//        int num = arr[top];
//        arr[top--] = 0;
//        return num;
//    }
//
//}


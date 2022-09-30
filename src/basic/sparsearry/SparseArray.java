package basic.sparsearry;

public class SparseArray {
    public static void main (String[] args){
        //创建原始二维数组,20行10列
        int [][] arr = new int[20][10];
        arr[14][5]=9;
        arr[3][6]=2;
        arr[15][8]=3;
        arr[9][9]=9;
        //遍历测试
        for(int row[]:arr){
            for(int i:row){
                System.out.print("\t"+i);
            }
            System.out.println();
        }
        //二维数组转换稀疏数组
        //1.获取有效值的个数
        int num=0;
        for(int i=0;i< arr.length;i++){//行数
            for(int j=0;j<arr[0].length;j++){
                num += arr[i][j]==0 ? 0:1;
            }
        }
        System.out.println(num);
        //2.创建稀疏数组
        int sparseArr[][] = new int[num+1][3];
        int cnt=0;
        sparseArr[0][0]=arr.length;sparseArr[0][1]=arr[0].length;sparseArr[0][2]=num;
        for(int i=0;i< arr.length;i++){//行数
            for(int j=0;j<arr[0].length;j++){
                if (arr[i][j] != 0) {
                    cnt++;
                    sparseArr[cnt][0]= i;
                    sparseArr[cnt][1]= j;
                    sparseArr[cnt][2]= arr[i][j];
                }
            }
        }
        for(int i=0; i<sparseArr.length;i++){
            System.out.println(sparseArr[i][0]+"\t"+sparseArr[i][1]+"\t"+sparseArr[i][2]);
        }
        //解压稀疏数组
        int [][] arr0= new int[sparseArr[0][0]][sparseArr[0][1]];
        for(int i=1;i<sparseArr.length;i++){//有效数字的个数
               arr0[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }
        for(int [] row:arr0){
            for(int i: row ){
                System.out.print(i+"\t");
            }
            System.out.println();
        }
    }
}

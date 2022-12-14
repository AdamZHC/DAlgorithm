package basic.algorithm;
//动态规划拿什么存不是关键，一维数组还是二维数组
//背包问题就是用一个二维数组，因为是0-1背包问题，用完这个东西，必须返回上一行
//也就是看你放了什么东西了，比如说你放了一个w[i]，这也是标识，那你肯定对应的能放w[i]的地方都失效了，也就是说放过这个物品就不能再放了
//那就需要去找w[i-1]去，就放这样的物品的最大值
public class DynamicPlanning {
    public static void main(String[] args){
        System.out.println(cuttingRope(10));
    }
    public static int cuttingRope(int n) {
        //动态规划思想:首先是从最一开始初始化记录结果的数组
        //记录对应的n的时候最大的乘积
        int[] mult = new int[n];
        //记录对应的n的时候分成了几段
        int[] m = new int[n];
        //记录要分析的该段结果
        int[] cur = new int[n];
        //记录上一次的具体分段结果
        int[] pre1 = new int[n];
        int[] pre2 = new int[n];
        //初始化前两次的情况
        mult[0] = 1;//当n=1的时候，乘积最大为1，其实根本不会出现这种情况，待会儿可以删掉
        m[0] = 1;
        pre2[0] = 1;
        mult[1] = 1;//当n=2的时候，乘积最大为1
        m[1] = 2;//分成两段
        pre1[0] = 1; pre1[1] = 1;
        //开始进行动态规划 -- 当绳子长度为n+1时的情况
        for(int i = 2; i < n; i++){//i就表示绳子长度的下标
            int mult1 = 0;//这是从绳子长度为n-1的时候最大的值
            int max = 0;
            for(int j = 0 ; j < m[i-1] ; j++){
                int temp = (mult[i-1]/pre1[j])*(pre1[j]+1); // 意思是把n对应的n-1情况里面某一个长度加1
                if(temp >= mult1){
                    mult1 = temp;
                    max = j;
                }
                cur[j] = pre1[j];
            }
            cur[max] = pre1[max]+1;
            int mult2 = mult[i-2]*2;
            //最后对于新获得的结果进行保存
            if(mult1 >= mult2){
                mult[i] = mult1;
                m[i] = m[i-1];
            }else{
                mult[i] = mult2;
                m[i] = m[i-1]+1;
                for(int j = 0 ; j<m[i-2];j++){
                    cur[j] = pre2[j];
                }
                cur[m[i]-1] = 2;
            }
            int[] temp = pre2;
            pre2 = pre1;
            pre1 = cur;
            cur = temp;
        }
        return mult[n-1];
    }
}

package leetcode;

public class LeetCode650 {
    public static void main(String[] args){
//        new Solution650().minSteps(111);
//        List<List<Integer>> basic.list = new ArrayList<List<Integer>>();
//        int a = 0;
//        int b = 0;
//        System.out.println("");
        //空串自动无
        String c = "Hello World    ";
        String[] ans = c.split(" ");
        System.out.println(ans[1] + "------");
    }
}
class Solution650 {
    //如果是动态规划的话，还需要判断是否有用到过复制过，是否copy也就是说需要一个集合
    //动规会把规模更小的每一次答案都输出来
    //最简单的暴力做法就是每一次都去找paste,用当前数字减去从最开始数字，然后看能paste
    //n次就dp[back][0] + dp[back][1] * n找一圈下来，直到最小的那个，时间复杂度为O(n2)
    //还有就是和复制之后的作比较
    //那就不值得用动规了，其实就是最初的暴力解法！！！暴力 + 动态规划
    //顺便生成素数表！！！利用素数极大地减少时间复杂度
    public int minSteps(int n) {
        //生成dp数组dp[i][0]表示此时最少的操作次数，dp[i][1]表示这时候可以paste的数
        //这时需要生成一个素数表 注：在1000以内共有168个素数
        if(n == 1) return 0;

        int[][]dp = new int[n + 1][2];
        int[]prime = new int[168];
        //初始化
        dp[1][0] = 0; dp[1][1] = 0;
        dp[2][0] = 2; dp[2][1] = 1;
        prime[0] = 2;
        //开始动态规划 i表示这时的字符数
        int point = 0;
        for(int i  = 3; i <= n; i ++){
            //生成素数表 和 寻找最小的同时进行，还有一种情况就是如果是偶数的话，那最小的情况一定是
            //除以2的商quo dp[quo][0] + 2; dp[quo][1] = quo;
            if(i % 2 == 0){
                dp[i][0] = dp[i / 2][0] + 2;
                dp[i][1] = i / 2;
            }else{
                boolean flag = false;
                //point总指向最后的那个素数
                for(int j = 0; j < 168;j ++){
                    //等于0说明是素数
                    if(prime[j] == 0){
                        prime[j] = i;
                        point = j;
                        flag = true;
                        break;
                    }
                    if(i % prime[j] == 0) break;
                }
                if(!flag){
                    int min = 1000, minPaste = 1000;
                    for(int k = 2; k < prime[point]; k ++){
                        //满足条件
                        if((i - k) % dp[k][1] == 0){
                            min = (dp[k][0] + (i - k) / dp[k][1]) <= min ?
                                    (dp[k][0] + (i - k) / dp[k][1]) : min;
                            minPaste = (dp[k][0] + (i - k) / dp[k][1]) <= min ?
                                    dp[k][1]: minPaste;
                        }
                    }
                    dp[i][0] = min;
                    dp[i][1] = minPaste;
                }else{
                    dp[i][0] = i;
                    dp[i][1] = 1;
                }
            }

        }
        return dp[n][0];
    }
}
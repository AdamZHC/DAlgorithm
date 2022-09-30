package codes.leetcode;

public class RegularExpression {
    public static void main(String[] args){
        //是因为没有输出，而不是因为没有初始化就不输出
        System.out.println(isMatch("aa","a*"));
    }
    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean dp[][] = new boolean[m][n];
        //把i = 0, j = 0放入循环比较优美
        for( int i = 0, j = 0;i < m && j < n;){
            if(s.charAt(i) == p.charAt(j)){
                dp[i][j] = true;
                i++;
                j++;
            }else{
                if(p.charAt(j) == '.'){
                    dp[i][j] = true;
                    for(int k = 0; k < m; k++){
                        dp[k][j] = true;
                    }
                    i++;
                    j++;
                    continue;
                }
                if(p.charAt(j) == '*'){
                    dp[i][j] = dp[i][j-1];
                    i++;
                    continue;
                }
                while(p.charAt(j) != '*'){
                    j++;
                }
                j++;
            }
        }
        return dp[m-1][n-1];
    }
}

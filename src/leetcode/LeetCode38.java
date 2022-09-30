package leetcode;

public class LeetCode38 {
    public static void main(String[] args) {
        new Solution38().countAndSay(3);
    }
}
class Solution38 {
    public String countAndSay(int n) {
        //可以打表到24
        String[] ans = new String[31];
        ans[1] = "1";
//        ans[2] = "11";
//        ans[3] = "21";
//        ans[4] = "1211";
//        ans[5] = "111221";
//        ans[6] = "312211";
//        ans[7] = "13112221";
//        ans[8] = "1113213211";
//        ans[9] = "31131211131221";
//        ans[10] = "13211311123113112211";
//        ans[11] = "11131221133112132113212221";
//        ans[12] = "3113112221232112111312211312113211";
//        ans[13] = "1321132132111213122112311311222113111221131221";
//        ans[14] = "11131221131211131231121113112221121321132132211331222113112211";
//        ans[15] = "311311222113111231131112132112311321322112111312211312111322212311322113212221";
        for(int k = 2; k <= n; k ++){
            String e = "";
            String s = ans[k - 1];
            for(int i = 0, cnt = 1; i < s.length() ;) {
                char ch = s.charAt(i);
                //这表示一个数符对
                while(i < s.length()){
                    if(i == s.length() - 1 || s.charAt(i + 1) != ch){
                        e = ans + ("" + cnt + ch);
                        i ++;
                        break;
                    }
                    cnt ++;
                    i ++;
                }
                cnt = 1;
            }
            ans[k] = e;
            return ans[k];
        }
        return ans[12];
    }
}
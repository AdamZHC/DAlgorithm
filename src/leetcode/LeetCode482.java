package leetcode;

public class LeetCode482 {
    public static void main(String[] args) {
        new Solution482().licenseKeyFormatting("5F3Z-2e-9-w", 4);
    }
}
class Solution482 {
    public String licenseKeyFormatting(String s, int k) {
        //字符数组会生成固定对象，所以还是要用StringBuffer
        StringBuffer ans = new StringBuffer();
        for(int i = s.length() - 1; i > -1; i --){
            char ch = s.charAt(i);
            if(ch == '-') continue;

            if(ch >= 'a' && ch <= 'z'){
                ans.append((char)(ch + 'A' - 'a'));
            }else{
                ans.append(ch);
            }
            if(ans.length() % k == 0 && ans.length() != 0){
                ans.append('-');
            }
        }
        return ans.reverse().toString();
    }
}
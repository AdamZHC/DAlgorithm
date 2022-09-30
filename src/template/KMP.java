package template;


import java.util.Arrays;

public class KMP {
    public static void main(String[] args) {
        int[] n1 = {1,0,0,1,0,0,0,1,0,1};
        int[] n2 = {1,1,0,0,1,1,1,0,0,0};
        System.out.println(new KMP().kmp(n2, n1,new KMP().kmpNext(n1, 0, 3), 0, 3));
    }
    public boolean kmp(int[] nums, int[] dest, int[] next, int start, int end){//str文本串  dest 模式串
        for(int i = 0, j = 0; i < nums.length; i++){
            while(j > 0 && nums[i] != dest[start + j]){
                j = next[j - 1];
            }
            if(nums[i] == dest[start + j]){
                j++;
            }
            if(j == end - start + 1){
                return true;
            }
        }
        return false;
    }
    public int[] kmpNext(int[] dest, int start, int end){
        int[] next = new int[end - start + 1];
        next[0] = 0;
        for(int i = 1, j = 0; i < end - start + 1; i++){
            while(j > 0 && dest[j + start] != dest[i + start]){
                j = next[j - 1];
            }
            if(dest[i + start] == dest[j + start]){
                j++;
            }
            next[i] = j;
        }
        return next;
    }

}

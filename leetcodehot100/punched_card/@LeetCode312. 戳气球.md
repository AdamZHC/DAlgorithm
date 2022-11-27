# @`LeetCode`312. 戳气球

```java
class Solution {
    public int[][] dp;
    public int f(int[] nums,int left,int right){
       if (left>right){
            return 0;
        }
        if (dp[left][right]!=-1){
            return dp[left][right];
        }
        int res=0;
        for (int i = left; i <=right ; i++) {
            res=Math.max(res,
                    f(nums,left,i-1)
                    +f(nums,i+1,right)
                    +nums[i]*nums[left-1]*nums[right+1]);
        }
        dp[left][right]=res;
        return res;
    }
    public int maxCoins(int[] nums) {
        if (nums.length==1){
            return nums[0];
        }
        int[] arr=new int[nums.length+2];
        arr[0]=1;
        System.arraycopy(nums,0,arr,1,nums.length);
        arr[arr.length-1]=1;
        dp=new int[arr.length][arr.length];
        for (int d = 0; d < dp.length; d++) {
            Arrays.fill(dp[d],-1);
        }
        return f(arr,1,arr.length-2);
    }
}
```


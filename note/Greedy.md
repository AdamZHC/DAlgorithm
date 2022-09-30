跳跃游戏应当用贪心的思路，局部最优得到全局最优

跳跃游戏一类的题都是这样处理的

### @正确贪心

```java
public class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

如果到不了最大的步数的话，直接返回false，如果可以的话，直接更新最远距离

```java
int example[] = {1,2,3,4,5,6,7,8,9};
```



```java
class Solution {
    public boolean canJump(int[] nums) {
        if(nums.length == 1) return true;
        int step = 0;
        int max = nums[step];
        int cnt = 0;
        while(step < nums.length && max < nums.length - 1){
            int tempStep = cnt++ == 0 ? 0 : nums[step] + step;
            step = max;
            //tempStep用来保存当前一个周期内不变的步数，这个需要控制跳出循环
            boolean flag = true;
            while(tempStep < step && step < nums.length - 1){
                if(nums[step] + step > max){
                    max = nums[step] + step;
                    flag = false;
                }
                step--;
            }
            if(flag) break;
        }
        return max >= nums.length - 1;
    }
}
//贪心去维护最大的距离
```

我的贪心思路多少有一点繁琐，我的意思是跳到最远的然后和之前的相比，然后能到最远的步数就去更新，也就是说是往回找，我这个思路就类似于动态规划和贪心的结合，可以说是动态规划的贪心优化
# @`LeetCode`15. 三数之和

```java
class Solution {
    int[] arr;
    final long add = (int)1e5 + 10;
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length; arr = nums;
        Set<Long> set = new HashSet<>();
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < n; ++i) {
            int l = i + 1, r = n - 1;
            while(l < r) {
                int sum = sum(i, l, r);
                if(sum == 0) {
                    long k = hash(i, l, r);
                    if(!set.contains(k)) {
                        set.add(k);
                        ans.add(u(i, l, r));
                    }
                    l++;
                    r--;
                } else {
                    if(sum < 0) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
        }
        return ans;
    }
    List<Integer> u(int a, int b, int c) {
        List<Integer> res = new ArrayList<>();
        res.add(arr[a]);
        res.add(arr[b]);
        res.add(arr[c]);
        return res;
    }
    int sum(int a, int b, int c) {
        return arr[a] + arr[b] + arr[c];
    }
    long hash(int a, int b, int c) {
        int max = Math.max(arr[a], Math.max(arr[b], arr[c]));
        int min = Math.min(arr[a], Math.min(arr[b], arr[c]));
        int mid = sum(a, b, c) - max - min;
        return get(min) * get(mid) * get(max);
    }
    long get(int a) {
        return (long)a + add;
    }

}	
```


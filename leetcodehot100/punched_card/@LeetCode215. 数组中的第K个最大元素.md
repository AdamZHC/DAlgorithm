# @`LeetCode`215. 数组中的第K个最大元素

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        return find(nums, 0, n - 1, n - k);
    }
    int find(int[] nums, int l ,int r, int k) {
        int kk = partition(nums, l, r);
        if(kk == k) 
            return nums[k];
        if(kk > k) {
            return find(nums, l, kk - 1, k);
        } else {
            return find(nums, kk + 1, r, k);
        }
    }
    int partition(int[] nums, int l, int r) {
        int pivot = l;
        while(l < r) {
            while(l < r && nums[r] >= nums[pivot])
                r--;
            while(l < r && nums[l] <= nums[pivot])
                l++;
            swap(nums, l, r);
        }
        swap(nums, l, pivot);
        return l;
    }
    void swap(int[] nums, int a, int b) {
        if(a == b)
            return;
        nums[a] = nums[a] ^ nums[b];
        nums[b] = nums[a] ^ nums[b];
        nums[a] = nums[a] ^ nums[b];
    }
}
```


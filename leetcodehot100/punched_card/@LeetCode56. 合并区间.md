# @`LeetCode`56. 合并区间

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        int n = intervals.length;
        List<int[]> ans = new ArrayList<>();
        int idx = 0;
        while(idx < n) {
            int l = intervals[idx][0], r = intervals[idx][1];
            while(idx < n && r >= intervals[idx][0]) {
                r = Math.max(r, intervals[idx][1]);
                idx++;
            }
            ans.add(new int[]{l, r});
        }
        int cnt = 0;
        int[][] lans = new int[ans.size()][2];
        for(int[] i : ans) {
            lans[cnt][0] = i[0];
            lans[cnt++][1] = i[1];
        }
        return lans;
    }
}
```


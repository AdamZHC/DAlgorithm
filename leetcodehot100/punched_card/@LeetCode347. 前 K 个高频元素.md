# @`LeetCode`347. 前 K 个高频元素

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int e : nums)
            map.put(e, map.getOrDefault(e, 0) + 1);
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> map.get(o1) - map.get(o2));
        int[] ans = new int[k];
        System.out.println(map);
        for(int key : map.keySet()) {
            if(pq.size() >= k) {
                int a = map.get(key);
                int b = map.get(pq.peek());
                if(a < b) {
                    continue;
                } else {
                    pq.poll();
                }
            }
            pq.offer(key);
        }
        // System.out.println(pq);
        for(int i = 0; i < k; ++i)
            ans[i] = pq.poll();
        return ans;
    }
}
```


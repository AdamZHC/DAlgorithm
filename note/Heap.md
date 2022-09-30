# @堆

经典堆思路——只要往进扔1个,然后根据指针一步步往前走, 自动排序
然后出队再入队，边出边入实现堆的最优化，这是两个数组的，m个数组可以
实现,注意这里需要判断是否访问过，要不然会太重复入队,而且只能用
List来实现对应的去重

```java
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((pair1, pair2) -> 
            nums1[pair1[0]] + nums2[pair1[1]] - nums1[pair2[0]] - nums2[pair2[1]]);
        heap.add(new int[]{i, 0});
        List<List<Integer>> res = new ArrayList<>();
        for(; k > 0 && !heap.isEmpty(); k--) {
            int[] pair = heap.poll();
            res.add(Arrays.asList(nums1[pair[0]], nums2[pair[1]]));
            if(pair[1] < nums2.length - 1) {
                heap.add(new int[] {pair[0], pair[1] + 1});
            }
            if(pair[0] < nums1.length - 1) {
                heap.add(new int[] {pair[0] + 1, pair[1]});
            }
        }
        return res;
    }
}
```
###延迟删除
延迟删除的思路——就是把对应应当删除的放到HashSet中(普遍处理方法)
然后应当判定的时候，先再HashSet判定一下，判断是否实现对应的操作
，这时候就用到延迟删除的技巧，就是不在应当删除的时候删除
```java
/**
 * 480. 滑动窗口中位数
 * @author wsq
 * @date 2021/2/3
 * 中位数是有序序列最中间的那个数。如果序列的长度是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
 *  例如：
 * [2,3,4]，中位数是 3
 * [2,3]，中位数是 (2 + 3) / 2 = 2.5
 * 给你一个数组 nums，有一个长度为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
 *
 * 示例：
 * 给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。
 * 窗口位置                      中位数
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 *  1 [3  -1  -3] 5  3  6  7      -1
 *  1  3 [-1  -3  5] 3  6  7      -1
 *  1  3  -1 [-3  5  3] 6  7       3
 *  1  3  -1  -3 [5  3  6] 7       5
 *  1  3  -1  -3  5 [3  6  7]      6
 *  因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。
 *
 * 链接：https://leetcode-cn.com/problems/sliding-window-median
 */
public class MedianSlidingWindow {
    public double[] medianSlidingWindow(int[] nums, int k){
        DualHeap dh = new DualHeap(k);
        for (int i = 0; i < k; i++) {
            dh.insert(nums[i]);
        }
        double[] res = new double[nums.length - k + 1];
        res[0] = dh.getMedian();

        for (int i = k; i < nums.length; i++){
            dh.insert(nums[i]);
            dh.erase(nums[i - k]);
            res[i - k + 1] = dh.getMedian();
        }
        return res;
    }
}

class DualHeap{
    // 大根堆，维护较小的一半元素
    private PriorityQueue<Integer> small;
    // 小根堆，维护较大的一半元素
    private PriorityQueue<Integer> large;

    // 由于优先队列只能从栈顶移除元素，因此需要采用延迟删除的思想，使用map来存储删除元素以及是数量
    private Map<Integer, Integer> delayed;
    // 窗口大小
    private int k;
    // small和large当前包含的元素个数，需要扣除【延迟删除】的个数
    private int smallSize, largeSize;

    public DualHeap(int k){
        this.small = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        this.large = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        this.delayed = new HashMap<>();
        this.k = k;
        this.smallSize = 0;
        this.largeSize = 0;
    }

    public double getMedian(){
        // 位运算求k是奇数还是偶数
        // 奇数返回small的栈顶
        // 偶数返回small 和 large的栈顶元素之和除以2
        return (k & 1) == 1 ? small.peek(): ((double)small.peek() + large.peek()) / 2;
    }

    /**
     * 插入新的元素值
     * 判断该元素值与small大顶堆的栈顶的大小
     * 小于small栈顶：将该元素加入到small中
     * 大于small栈顶：将该元组加入到large中
     * 最后进行balance
     * @param n
     */
    public void insert(int n){
        if (small.isEmpty() || n <= small.peek()){
            small.offer(n);
            smallSize++;
        }else {
            large.offer(n);
            largeSize++;
        }
        makeBalance();
    }

    /**
     * 删除某个元素值
     * 根据元素值，判断元素是在那个堆中
     * @param n
     */
    public void erase(int n){
        delayed.put(n, delayed.getOrDefault(n, 0) + 1);
        if (n <= small.peek()){
            --smallSize;
            if (n == small.peek()){
                prune(small);
            }
        }else{
            --largeSize;
            if (n == large.peek()){
                prune(large);
            }
        }
        makeBalance();
    }

    /**
     * 执行慢删除操作
     * @param heap
     */
    private void prune(PriorityQueue<Integer> heap){
        while (!heap.isEmpty()){
            int num = heap.peek();
            if (delayed.containsKey(num)){
                delayed.put(num, delayed.get(num)-1);
                if (delayed.get(num) == 0){
                    delayed.remove(num);
                }
                heap.poll();
            }else{
                break;
            }
        }
    }

    /**
     * balance操作是维持大顶堆和小顶堆的数量之差为1
     * smallSize = largeSize + 1
     * 同时，如果在更换小顶堆和大顶堆的栈顶后，需要将对应的堆进行延迟删除的操作
     */
    private void makeBalance(){
        if (smallSize > largeSize + 1){
            large.offer(small.poll());
            --smallSize;
            ++largeSize;
            prune(small);
        }else if (smallSize < largeSize){
            small.offer(large.poll());
            ++smallSize;
            --largeSize;
            prune(large);
        }
    }
}

```


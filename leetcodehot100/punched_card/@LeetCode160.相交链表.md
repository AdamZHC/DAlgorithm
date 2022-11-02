# @`LeetCode`160.相交链表

### 思路：

这个题目印象太深了！思路其实不简单，就是**核心思路**就是考虑到如果**某个遍历算法可以实现遍历结点数相同**，那么就一定可以直到目的结点，只需要让`A`链表遍历到尾部的时候，再去`B`的头部遍历，这样的话遍历节点数总是`skipA + skipB + intersection` 另外无相交部分的话就用`cnt`变量测算**是否超过两次遍历**链表

#### 这个算法还有一个很**浪漫**的描述：

**走到尽头见不到你，于是走过你来时的路，等到相遇时才发现，你也走过我来时的路**



```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode ta = headA, tb = headB;
        int cnt = 2;
        while(ta != tb) {
            ta = ta.next == null ? headB : ta.next;
            tb = tb.next == null ? headA : tb.next;
            if(ta == headB)
                cnt--;
            if(cnt < 0)
                return null;
        }
        return ta;
    }
}
```


# @`LeetCode`141.环形链表

### 思路

链表中经典的思路——**快慢指针**

如果有环的情况，总可以使得快慢指针在最后相遇，另外选择`step == 2`的原因就是两者的距离总是可以被两者速度之差`dv = 1`整除

如果无环的话，快指针会先遇到`n2.next == null || n2 == null`因此加上这个特判情况就可以排除

```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode n1 = head, n2 = head;
        while(n2 != null && n2.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
            if(n1 == n2)
                return true;
        }
        return false;
    }
}
```


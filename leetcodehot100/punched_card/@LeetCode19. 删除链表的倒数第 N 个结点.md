# @`LeetCode`19. 删除链表的倒数第 N 个结点

### 思路

通过一个辅助指针`bt`，首先令辅助指针移动`n`个结点，之后删除指针`pt`和`bt`同时前移，直到辅助指针`bt.next == null`此时说明`bt`指向待删除指针的前一个位置，另外需要注意有可能出现只有一个结点的情况，此时需要特判一波

### 代码

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pt = head, bt = head;
        while(n-- != 0) 
            bt = bt.next;
        if(bt == null)
            return head.next;
        while(bt.next != null) {
            pt = pt.next;
            bt = bt.next;
        }
        pt.next = pt.next.next;
        return head;

    }
}
```


# @`LeetCode`206.反转链表

### 思路

迭代法很简单，主要说一下递归法

这里递归有点不容易想

主要是考虑到递归之后的`head.next`指向了最后一个结点，因此这里就是直接

`head.next.next = head`

同时防止出现环形链表需要`head.next = null`

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
    public ListNode reverseList(ListNode head) {
        return dfs(head);
    }
    ListNode dfs(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode res = dfs(head.next);
        head.next.next = head;
        head.next = null;
        return res;
    }
}
```


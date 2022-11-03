# @`LeetCode`142.环形链表Ⅱ

### 思路

这个题目刚开始没有想到怎么做，后来是用**数学公式**推导得到的解法

大致思路就是分别令部分的长度为`a b c`，根据他们相遇时的路程关系，可以推导出环长度和**快慢路程**的关系最终发现如果使**第一次相遇**之后，令**指针**指向头部，**第二次相遇**时恰好可以指向**入环结点**

具体的推导在评论区有很多大佬的推导

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
    public ListNode detectCycle(ListNode head) {
        ListNode n1 = head, n2 = head;
        while(n2 != null && n2.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
            if(n1 == n2) {
                n2 = head;
                while(n1 != n2) {
                    n1 = n1.next;
                    n2 = n2.next;
                }
                return n1;
            }
        }
        return null;
    }
}
```


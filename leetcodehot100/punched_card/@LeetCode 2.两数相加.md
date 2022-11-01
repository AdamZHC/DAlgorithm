# @`LeetCode` 2.两数相加

### 题面

注意题面，给定的两个数都是逆序存储的，因此直接从两链表头结点开始遍历即可（如果是正序存储的话，需要遍历到最后一个位置）

### 思路

普通的链表遍历，注意在过程中维护变量`ll`表示是否进位，另外为了代码简洁一点，我把两种情况合到了一起

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode h1 = l1, h2 = l2, ans = new ListNode();
        ListNode tmp = ans;
        int ll = 0;
        while(h1 != null || h2 != null) {
            int v1 = h1 == null ? 0 : h1.val;
            int v2 = h2 == null ? 0 : h2.val;
            tmp.next = new ListNode((v1 + v2 + ll) % 10);
            ll = (v1 + v2 + ll) / 10;
            if(h1 != null)
                h1 = h1.next;
            if(h2 != null)
                h2 = h2.next;
            tmp = tmp.next;
        }
        if(ll > 0)
            tmp.next = new ListNode(1);
        return ans.next;
    }
}
```


# @`LeetCode`21. 合并两个有序链表

### 思路：

类似于昨天的"两数相加"，同样是分别维护两个指针来实现合并，有意思的是这个过程就是**归并排序**的部分**合并部分**，另外在`nlogn`的排序算法中，会考察链表排序的也就是归并排序了，另外注意把两种情况合到一起，这是基于**有数据范围**的，如果没有数据范围的话，那就只能分**三种情况**

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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        ListNode tmp = head;
        while(list1 != null || list2 != null) {
            int v1 = list1 == null ? 101 : list1.val;
            int v2 = list2 == null ? 101 : list2.val;
            if(v1 < v2) {
                tmp.next = new ListNode(v1);
                list1 = list1.next;
            } else {
                tmp.next = new ListNode(v2);
                list2 = list2.next;
            }
            tmp = tmp.next;
        }
        return head.next;
    }
}
```


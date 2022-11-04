# @`LeetCode`23.合并`K`个升序链表

### 思路

没什么复杂的，和之前的合并两个的思路一摸一样

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
    final int INF = 10001;
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode ans = new ListNode();
        ListNode tmp = ans;
        while(true) {
            int kk = min(lists);
            if(kk == -1)
                break;
            ListNode min = lists[kk];
            tmp.next = min;
            lists[kk] = lists[kk].next;
            tmp = tmp.next;
        }
        return ans.next;
    }
    boolean chk(ListNode[] lists) {
        for(ListNode e : lists)
            if(e != null)
                return true;
        return false;
    }
    int min(ListNode[] lists) {
        int res = -1, n = lists.length, cc = INF;
        for(int i = 0; i < n; ++i) 
            if(lists[i] != null) {
                res = lists[i].val < cc ? i : res;
                cc = Math.min(cc, lists[i].val);
            }
                
        return res;
    }
}
```


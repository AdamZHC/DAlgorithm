# @`LeetCode`234.回文链表

不使用复杂的方法，就直接放到`arr`也就是数组里面，然后通过左右指针判定是否为回文数组就可以

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
    public boolean isPalindrome(ListNode head) {
        int s = ss(head);
        int sss = s;
        int[] arr = new int[s];
        while(s-- != 0) {
            arr[s] = head.val;
            head = head.next;
        }
        for(int i = 0; i < sss; ++i) {
            int r = sss - i - 1;
            if(arr[i] != arr[r])
                return false;
        }
        return true;
    }
    int ss(ListNode head) {
        int res = 0;
        while(head != null) {
            head = head.next;
            res++;
        }
        return res;
    }
}
```


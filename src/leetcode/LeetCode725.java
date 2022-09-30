package leetcode;

public class LeetCode725 {
    public static void main(String[] args) {
        ListNode head  = new ListNode(1);
        ListNode temp = head;
        for( int i = 2; i < 6; i ++){
            ListNode ln = new ListNode(i);
            temp.next = ln;
            temp = temp.next;

        }
        new Solution725().splitListToParts(head,5);
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution725 {
    public ListNode[] splitListToParts(ListNode head, int k) {
        //暴力解决 -- 先去获取单链表的长度-- 还是挺痛快，都记得怎么写
        int len = 1;
        ListNode temp = head;
        while(temp.next != null){
            temp = temp.next;
            len ++;
        }
        int quo = len / k;
        int remain = len % k;
        temp = head;
        ListNode[] ans = new ListNode[k];
        //表示要分成五份
        for(int i = 0; i < k; i ++){
            ListNode lnHead = new ListNode();
            ListNode help = lnHead;
            for(int j = 0; j < quo + (remain == 0 ? 0 : 1);j ++){
                if(j == 0) lnHead.val = temp.val;
                else{
                    ListNode node = new ListNode(temp.val);
                    help.next = node;
                    help = node;
                }
                temp = temp.next;
            }
            ans[i] = lnHead;
            remain --;
        }
        return ans;
        // ListNode length = new ListNode(len);
        // ListNode[] ans = new ListNode[1];
        // ans[0] = length;
        // return ans;
    }
}
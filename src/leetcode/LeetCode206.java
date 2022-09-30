package leetcode;


import java.util.*;

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
class LeetCode206 {
    public static void main(String[] args) {
        Integer[] nums = {3, 2, 1};
        Arrays.sort(nums,new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        List<Integer> list = new ArrayList<>();
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        PriorityQueue<Integer> p = new PriorityQueue<>();
        Set<Integer> set = new HashSet<>();
        new ArrayList<>(set);
        System.out.println(Arrays.toString(nums));
    }
    public ListNode reverseList(ListNode head) {
        //规模最小的时候,
        //也就是递归的最深的部分
        //这时候要理解一个点就是在于对同一个链表进行操作，在堆内存中操作
        if(head == null){
            return null;
        }
        if(head.next == null){
            //完成反转
            return head;
        }else{
            //遍历到最底部
            ListNode temp = reverseList(head.next);
            ListNode cur = temp;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = head;
            head.next = null;
            head = cur ;
            return head;
        }
    }
}
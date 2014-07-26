/*
Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.
   After removing the second node from the end, the linked list becomes 1->2->3->5.

Note:
Given n will always be valid.
Try to do this in one pass.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
// TC: O(n), SC: O(1)
// one pass
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p1 = dummy;
        ListNode p2 = dummy;
        while (n-- > 0) {
            p2 = p2.next;
        }
        while (p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        p1.next = p1.next.next;
        return dummy.next;
    }
}
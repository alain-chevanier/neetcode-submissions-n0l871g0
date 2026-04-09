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
        // O(head.length) time +  O(1) space
        // 1. reverse the list
        ListNode reversed = reverse(head);
        // 2. remove the n-th (1-based) node
        ListNode removed = removeAt(reversed, n);
        // 3. reverse the list again and return ir
        return reverse(removed);
    }

    ListNode reverse(ListNode head) {
        ListNode it = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = it;
            it = head;
            head = next;
        }
        return it;
    }

    ListNode removeAt(ListNode node, int position) {
        ListNode head = new ListNode();
        ListNode it = head;
        head.next = node;

        while (position-- > 1) {
            it = it.next;
        }

        it.next = it.next != null ? it.next.next : null;
        return head.next;
    }
}

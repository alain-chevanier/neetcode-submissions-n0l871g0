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
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode it1 = dummy, it2 = dummy;
        while (n-- > 0) {
            it2 = it2.next;
        }
        //System.out.println("it2: " + it2.val);

        while (it2.next != null) {
            it2 = it2.next;
            it1 = it1.next;
        }

        it1.next = it1.next == null ? null : it1.next.next;
        return dummy.next;
    }

    public ListNode removeNthFromEnd1(ListNode head, int n) {
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
        head.next = node;

        ListNode it = head;
        while (position-- > 1) {
            it = it.next;
        }

        it.next = it.next != null ? it.next.next : null;
        return head.next;
    }
}

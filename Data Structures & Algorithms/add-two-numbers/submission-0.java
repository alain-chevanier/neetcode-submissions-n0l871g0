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
        return aux(l1, l2, 0);
    }

    ListNode aux(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null) {
            return carry > 0 ? new ListNode(carry) : null;
        }

        int l1Val = l1 == null ? 0 : l1.val;
        int l2Val = l2 == null ? 0 : l2.val;

        ListNode l1Next = l1 == null ? null : l1.next;
        ListNode l2Next = l2 == null ? null : l2.next;

        int val = l1Val + l2Val + carry;
        ListNode node = new ListNode(val % 10);
        node.next = aux(l1Next, l2Next, val / 10);
        return node;
    }
}

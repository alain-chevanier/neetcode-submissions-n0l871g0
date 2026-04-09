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
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>((l1, l2) -> l1.val - l2.val);
        for (var list : lists) {
            if (list != null) {
                heap.offer(list);
            }
        }
        ListNode dummy = new ListNode();
        ListNode it = dummy;
        while (heap.size() > 0) {
            var list = heap.poll();
            it.next = list;
            it = it.next;
            if (list.next != null) {
                heap.offer(list.next);
            }
        }
        return dummy.next;
    }
}

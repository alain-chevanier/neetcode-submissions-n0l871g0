/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        // map node in original to same node in copy
        Map<Node, Node> originalToCopy = new HashMap<>();
        
        // create  a copy of each node and add it to original to copy
        Node dummy = new Node(0);
        Node it = dummy;
        Node headCopy = head;
        while (head != null) {
            Node copy = new Node(head.val);
            originalToCopy.put(head, copy);
            // add it to the new list
            it.next = copy;
            it = it.next;
            head = head.next;
        }

        head = headCopy;
        while (head != null) {
            Node copy = originalToCopy.get(head);
            if (head.random != null) {
                copy.random = originalToCopy.get(head.random);
            } else {
                copy.random = null;
            }
            
            head = head.next;
        }
        return dummy.next;

    }
}

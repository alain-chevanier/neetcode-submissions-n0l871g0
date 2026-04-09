/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> visibleNodes = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        if (root != null) {
            queue.offer(root);
        }
        
        while (queue.size() > 0) {
            Deque<TreeNode> levelNodes = new LinkedList(queue);
            queue.clear();
            
            visibleNodes.add(levelNodes.peekLast().val);
            while (levelNodes.size() > 0) {
                var node = levelNodes.removeFirst();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return visibleNodes;
    }
}

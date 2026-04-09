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
    public int goodNodes(TreeNode root) {
        return goodNodeAux(root, Integer.MIN_VALUE);
    }

    int goodNodeAux(TreeNode node, int max) {
        if (node == null) {
            return 0;
        }
        int newMax = Math.max(max, node.val);
        return goodNodeAux(node.left, newMax) + goodNodeAux(node.right, newMax) 
            + (node.val >= max ? 1 : 0);
    }
}

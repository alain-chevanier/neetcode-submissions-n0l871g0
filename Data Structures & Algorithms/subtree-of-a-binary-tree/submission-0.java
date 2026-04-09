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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return subRoot == null;
        }
        return isSame(root, subRoot)
            || isSubtree(root.left, subRoot)
            || isSubtree(root.right, subRoot);
    }

    boolean isSame(TreeNode tree, TreeNode subtree) {
        if (tree == null && subtree == null) {
            return true;
        }

        if (tree == null || subtree == null) {
            return false;
        }

        return tree.val == subtree.val
            && isSame(tree.left, subtree.left)
            && isSame(tree.right, subtree.right);
    }
}

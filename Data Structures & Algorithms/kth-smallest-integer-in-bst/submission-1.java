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
    int position;
    int valueAtK;
    int k;

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        inorder(root);
        return valueAtK;
    }

    void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        position++;
        if (position == k) {
            valueAtK = node.val;
            return;
        }
        inorder(node.right);
    }
}

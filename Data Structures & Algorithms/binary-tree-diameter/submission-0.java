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
    Map<TreeNode, Integer> height;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        height = new HashMap<>();
        // in linear time we calculate the height of every node 
        maxDepth(root);
        // the diameter is the max the values of height(node.left) + height(node.right) - 2;
        return diameter(root);
    }

    int diameter(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = node.left == null ? 0 : height.get(node.left);
        int rightHeight = node.right == null ? 0 : height.get(node.right);
        int myDiameter = leftHeight + rightHeight;
        int leftDiameter = diameter(node.left);
        int rightDiameter = diameter(node.right);
        return Math.max(myDiameter, Math.max(leftDiameter, rightDiameter));
    }

    int maxDepth(TreeNode node) {
        if (node ==null) {
            return 0;
        }
        if (height.containsKey(node)) {
            return height.get(node);
        }
        int h = 1 + Math.max(maxDepth(node.left), maxDepth(node.right));
        height.put(node, h);
        return h;
    }
}

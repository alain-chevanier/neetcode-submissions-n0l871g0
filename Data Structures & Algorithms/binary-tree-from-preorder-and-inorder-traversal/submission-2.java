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
    int[] inorderValToIdx;
    int[] preorder;
    int[] inorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        inorderValToIdx = new int[2001];
        for (int i = 0; i < inorder.length; i++) {
            // inorderValToIdx.put(inorder[i], i);
            inorderValToIdx[inorder[i] + 1000] = i;
        }

        this.preorder = preorder;
        this.inorder = inorder;

        int high = preorder.length-1;
        
        return aux(preorder, 0, high,  
                    inorder, 0, high);
    }

    TreeNode aux(int[] preorder, int lowP, int highP,
                int[] inorder, int lowI, int highI) {
        //System.out.printf("preorder low: %d, high: %d\n", lowP, highP);
        //System.out.printf("inorder low: %d, high: %d\n\n", lowI, highI);
        if (lowP > highP) {
            return null;
        }
        int rootValue = preorder[lowP];
        TreeNode root = new TreeNode(rootValue);
        
        int idx = inorderValToIdx[rootValue + 1000]; //  inorderIdx(rootValue, inorder, lowI, highI);
        int leftTreeSize = idx - lowI;
        root.left = aux(preorder, lowP+1, lowP+leftTreeSize,
                        inorder, lowI, idx-1);
        root.right = aux(preorder, lowP+leftTreeSize+1, highP,
                        inorder, idx+1, highI);
        return root;
    }

    int inorderIdx(int value, int[] inorder, int low, int high) {
        while (low <= high) {
            if (inorder[low] == value) {
                return low;
            }
            low++;
        }
        return -1;
    }
}

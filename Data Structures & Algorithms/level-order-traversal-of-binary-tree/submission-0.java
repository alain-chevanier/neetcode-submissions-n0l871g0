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
    Map<Integer,List<Integer>> levelList;

    public List<List<Integer>> levelOrder(TreeNode root) {
        levelList = new HashMap<>();

        if (root != null) {
            bfs(root);
        }

        return levelList.entrySet()
            .stream()
            .map(e -> e.getValue())
            .toList();
    }

    void bfs(TreeNode node) {
        Queue<TreeWrap> queue = new LinkedList<>();
        queue.offer(new TreeWrap(node, 0));
        while (queue.size() > 0) {
            var wrap = queue.poll();
            if (!levelList.containsKey(wrap.level)) {
                levelList.put(wrap.level, new LinkedList<>());
            }
            levelList.get(wrap.level).add(wrap.node.val);
            if (wrap.node.left != null) {
                queue.offer(new TreeWrap(wrap.node.left, wrap.level+1));
            }
            if (wrap.node.right != null) {
                queue.offer(new TreeWrap(wrap.node.right, wrap.level+1));
            }
        }
    }

}

class TreeWrap {
    TreeNode node;
    int level;

    TreeWrap(TreeNode node, int level) {
        this.node = node;
        this.level = level;
    }
}

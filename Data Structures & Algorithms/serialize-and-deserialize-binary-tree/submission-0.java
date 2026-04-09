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

public class Codec {
Map<TreeNode,Integer> positions;
    int position;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        
        // root -> preorder | inorder
        // if the tree contains duplicates we need to 
        // add and extra data to make then unique

        positions = new HashMap<>();
        position = 0;
        List<String> preorderList = new LinkedList<>();
        List<String> inorderList = new LinkedList<>();

        preorder(root, preorderList);
        inorder(root, inorderList);

        String preorderStr = preorderList.stream()
                                .map(Object::toString)
                                .collect(Collectors.joining(","));
        String inorderStr = inorderList.stream()
                                .map(Object::toString)
                                .collect(Collectors.joining(","));
        String encoding = preorderStr + "X" + inorderStr;
        // System.out.println("encoding: " + encoding);
        return encoding;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // preorder | inorder -> root
        String[] traversals = data.split("X");
        
        String[] preorder = traversals.length > 0
            ? traversals[0].split(",") : new String[0];
        String[] inorder = traversals.length > 1 
            ? traversals[1].split(",") : new String[0];
       
        Map<String, Integer> numToPos = new HashMap<>();

        for(int i = 0; i < inorder.length; i++) {
            numToPos.put(inorder[i], i);
        }

        return rebuild(preorder, 0, preorder.length-1,
                       inorder,  0, inorder.length-1,
                       numToPos);
        
    }

    TreeNode rebuild(String[] preorder, int begP, int endP,
                    String[] inorder, int begI, int endI,
                    Map<String, Integer> numToPos) {
        if (begP > endP) {
            return null;
        }
        String num = preorder[begP];
        int myNum = Integer.parseInt(num.split("_")[0]);
        TreeNode res = new TreeNode(myNum);
        int pos = numToPos.get(num);
        int leftSize = numToPos.get(num) - begI;
        res.left = rebuild(preorder, begP+1, begP+leftSize,
                          inorder, begI, pos-1,
                          numToPos);
        res.right = rebuild(preorder, begP+leftSize+1, endP,
                            inorder, pos+1, endI,
                            numToPos);
        return res;
    }

    void preorder(TreeNode node, List<String> list) {
        if (node == null) {
            return;
        }
        positions.put(node,position++);
        list.add(node.val+"_"+positions.get(node));
        preorder(node.left, list);
        preorder(node.right, list);
    }

    void inorder(TreeNode node, List<String> list) {
        if (node == null) {
            return;
        }
        inorder(node.left, list);
        list.add(node.val+"_"+positions.get(node));
        inorder(node.right, list);
    }
}

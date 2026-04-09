/*
Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        // copy current node 
        Map<Node, Node> map = new HashMap<>();
        
        cloneNodeAux(node, map);

        return map.get(node);
    }

    void cloneNodeAux(Node node, Map<Node, Node> map) {
        if (map.containsKey(node) || node == null) {
            return;
        }
        Node cur = new Node(node.val);
        map.put(node, cur);
        for (var n : node.neighbors) {
            cloneNodeAux(n, map);
            cur.neighbors.add(map.get(n));
        }
        
    }
}
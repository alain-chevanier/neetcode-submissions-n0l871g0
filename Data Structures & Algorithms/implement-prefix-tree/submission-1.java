class PrefixTree {
    TrieNode trie;

    public PrefixTree() {
        trie = new TrieNode();
    }

    public void insert(String word) {
        trie.insert(word);
    }

    public boolean search(String word) {
        return trie.search(word);
    }

    public boolean startsWith(String prefix) {
        return trie.startsWith(prefix);
    }
}

class TrieNode {
    TrieNode[] children;
    boolean isEnd;

    TrieNode() {
        this.children = new TrieNode[26];
        this.isEnd = false;
    }

    void insert(String str) {
        TrieNode node = this;
        int idx = 0;
        while (idx < str.length()) {
            int c = str.charAt(idx) - 'a';
            if (node.children[c] == null) {
                node.children[c] = new TrieNode();
            }
            idx++;
            node = node.children[c];
        }
        node.isEnd = true;
    }

    boolean search(String str) {
        TrieNode node = findNode(str);
        return node !=null && node.isEnd;
    }

    boolean startsWith(String str) {
        TrieNode node = findNode(str);
        return node != null;
    }

    TrieNode findNode(String str) {
        TrieNode node = this;
        int idx = 0;
        while (idx < str.length()) {
            int c = str.charAt(idx) - 'a';

            if (node.children[c] == null) {
                return null;
            }

            idx++;
            node = node.children[c];
        }
        return node;
    }
}

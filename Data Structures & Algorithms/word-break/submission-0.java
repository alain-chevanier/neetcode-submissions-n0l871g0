class Solution {
    String str;
    List<String> dictionary;
    Trie trie;
    Map<Integer, Boolean> memo;

    public boolean wordBreak(String s, List<String> wordDict) {
        this.str = s;
        this.dictionary = wordDict;
        this.memo = new HashMap<>();
        this.trie = new Trie(wordDict);
        return aux(0);
    }

    boolean aux(int idx) {
        if (idx == this.str.length()) {
            return true;
        }
        if (this.memo.containsKey(idx)) {
            return this.memo.get(idx);
        }
        boolean hasSolution = false;
        for (int len = 1; idx+len <= this.str.length() && !hasSolution; len++) {
            String substr = this.str.substring(idx, idx+len);
            if (this.trie.startsWith(substr)) {
                if (this.trie.search(substr)) {
                    hasSolution = aux (idx + len);
                }
            } else {
                break;
            }
        }
        this.memo.put(idx, hasSolution);
        return hasSolution;
    }
}

class Trie {
    TrieNode root;

    Trie(List<String> dictionary) {
        this.root = new TrieNode();
        for (String word : dictionary) {
            this.insert(word);
        }
    }

    void insert(String word) {
        TrieNode cur = this.root;
        for (char c : word.toCharArray()) {
            int cIdx = c - 'a';
            if (cur.children[cIdx] == null) {
                cur.children[cIdx] = new TrieNode();
            }
            cur = cur.children[cIdx];
        }
        cur.isEnd = true;
    }

    boolean search(String word) {
        TrieNode node = getLastNode(word);
        return node != null && node.isEnd;
    }

    boolean startsWith(String prefix) {
        TrieNode node = getLastNode(prefix);
        return node != null;
    }

    TrieNode getLastNode(String str) {
        TrieNode cur = this.root;
        for (char c : str.toCharArray()) {
            int cIdx = c - 'a';
            if (cur.children[cIdx] == null) {
                return null;
            }
            cur = cur.children[cIdx];
        }
        return cur;
    }
}

class TrieNode {
    static final int DISTINCT_CHARS = 26;
    TrieNode[] children;
    boolean isEnd;

    TrieNode() {
        this.children = new TrieNode[DISTINCT_CHARS];
        this.isEnd = false;
    }
}

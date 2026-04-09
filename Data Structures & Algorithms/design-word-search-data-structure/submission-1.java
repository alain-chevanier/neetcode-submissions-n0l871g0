class WordDictionary {
    TrieNode trie;

    public WordDictionary() {
        this.trie = new TrieNode();
    }

    public void addWord(String word) {
        trie.insert(word);
    }

    public boolean search(String word) {
        return trie.search(word);
    }
}

class TrieNode {
    TrieNode[] children;
    boolean isEnd;

    TrieNode() {
        this.children = new TrieNode[26];
        this.isEnd = false;
    }

    void insert(String word) {
        int idx = 0,
            len = word.length();
        TrieNode node = this;

        while (idx < len) {
            int c = word.charAt(idx) - 'a';
            if (node.children[c] == null) {
                node.children[c] = new TrieNode();
            }
            node = node.children[c];
            idx++;
        }
        node.isEnd = true;
    }

    boolean search(String word) {
        return searchAux(this, word, 0);
    }

    static boolean searchAux(TrieNode node, String word, int wordIdx) {
        if (wordIdx >= word.length()) {
            return node != null && node.isEnd;
        }
        if (node == null) {
            return false;
        }

        char c = word.charAt(wordIdx);
        if (c == '.') {
            // at least one children is not null
            // and any of those can be matched 
            for (int x = 0; x < node.children.length; x++) {
                if (node.children[x] != null &&
                    searchAux(node.children[x], word, wordIdx+1)) {
                    return true;
                }
            }
            return false;
        } else {
            int cIdx = c - 'a';
            if (node.children[cIdx] != null) {
                return searchAux(node.children[cIdx], word, wordIdx+1);
            }
            return false;
        }
    }
}
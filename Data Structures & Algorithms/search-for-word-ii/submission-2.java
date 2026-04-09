class Solution {
    static final int LETTERS = 26;
    static final int[][] DIRECTIONS = new int[][] {
        {0,1}, {0,-1}, {1,0}, {-1, 0}
    };
    boolean[][] visited;
    char[][] board;
    TrieNode trie;
    //List<String> answer;
    HashSet<String> distinctWords;

    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        this.trie = new TrieNode();
        for (var word : words) {
            this.trie.insert(word);
        }

        this.distinctWords = new HashSet<>();
        //this.answer = new LinkedList<>();

        int rows = board.length;
        int cols = board[0].length;
        this.visited = new boolean[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                generateWords(r, c, "");
            }
        }

        return new LinkedList<>(this.distinctWords);
    }

    void generateWords(int row, int col, String word) {
        
        
        String newWord = word + board[row][col];
        // System.out.println(newWord);
        if (!this.trie.isPrefix(newWord)) {
            return;
        }

        this.visited[row][col] = true;

        if (this.trie.isWord(newWord)) {
            this.distinctWords.add(newWord);
        }

        for (var dir : DIRECTIONS) {
            int r = row + dir[0];
            int c = col + dir[1];
            if (isPositionInvalid(r, c) 
                || this.visited[r][c]) {
                    continue;
                }
            
            generateWords(r, c, newWord);
            
        }

        this.visited[row][col] = false;
    }

    boolean isPositionInvalid(int row, int col) {
        return row < 0 || col < 0 
        || row >= this.board.length || col >= this.board[0].length;
    }

    static class TrieNode {
        TrieNode[] children;
        boolean isEnd;

        TrieNode() {
            this.children = new TrieNode[LETTERS];
            this.isEnd = false;
        }

        void insert(String word) {
            TrieNode node = this;
            int idx = 0;
            while (idx < word.length()) {
                int charIdx = word.charAt(idx) - 'a';
                if (node.children[charIdx] == null) {
                    node.children[charIdx] = new TrieNode();
                }
                node = node.children[charIdx];
                idx++;
            }
            node.isEnd = true;
        }

        boolean isWord(String word) {
            var node = findNode(word);
            return node.map(n -> n.isEnd).orElse(false);
        }

        boolean isPrefix(String prefix) {
            var node = findNode(prefix);
            return node.isPresent();
        }

        Optional<TrieNode> findNode(String word) {
            TrieNode node = this;
            int idx = 0;
            while (idx < word.length()) {
                int charIdx = word.charAt(idx) - 'a';
                if (node.children[charIdx] == null) {
                    return Optional.empty();
                }
                node = node.children[charIdx];
                idx++;
            }
            return Optional.of(node);
        }
    }
}



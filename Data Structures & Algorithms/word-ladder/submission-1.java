class Solution {
    Map<String, Integer> wordToNum;
    Map<Integer,Set<Integer>> adj;

    public int ladderLength(String beginWord, 
            String endWord, 
            List<String> wordList) {
        this.wordToNum = new HashMap<>();
        this.adj = new HashMap<>();
        wordList.add(0, beginWord);

        for (int i = 0; i < wordList.size(); i++) {
            adj.put(i, new HashSet<>());
        }

        int nextId = 0;
        String[] words = wordList.toArray(new String[wordList.size()]);
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (!wordToNum.containsKey(word)) {
                wordToNum.put(word, nextId++);
            }
            int wordId = wordToNum.get(word);
            for (int j = i + 1; j < words.length; j++) {
                String nextWord = words[j];
                if (!wordToNum.containsKey(nextWord)) {
                    wordToNum.put(nextWord, nextId++);
                }
                int nextWordId = wordToNum.get(nextWord);
                if (areConnected(word, nextWord)) {
                    adj.get(wordId).add(nextWordId);
                    adj.get(nextWordId).add(wordId);
                }
            }
        }

        if (!wordToNum.containsKey(endWord)) {
            return 0;
        }
        
        int begId = wordToNum.get(beginWord);
        int endId = wordToNum.get(endWord);

        return bfs(begId, endId);
    }

    boolean areConnected(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        int diffs = 0;
        for (int i = 0; i < a.length() && diffs < 2; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diffs++;
            }
        }
        return diffs == 1;
    }

    int bfs(int src, int dst) {
        int[] elemsInSeq = new int[this.wordToNum.size()];
        elemsInSeq[src] = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);

        while (queue.size() > 0) {
            int cur = queue.poll();
            if (cur == dst) {
                return elemsInSeq[cur];
            }
            for (int neigh : this.adj.get(cur)) {
                if (elemsInSeq[neigh] > 0) {
                    continue;
                }
                elemsInSeq[neigh] = elemsInSeq[cur] + 1;
                queue.offer(neigh);
            }
        }
        
        return 0;
    }
}

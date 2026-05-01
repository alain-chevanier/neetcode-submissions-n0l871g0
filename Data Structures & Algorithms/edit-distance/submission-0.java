class Solution {
    String source;
    String target;
    Integer[][] memo;

    public int minDistance(String word1, String word2) {
        this.source = word1;
        this.target = word2;
        this.memo = new Integer[word1.length() + 1][word2.length() + 1];
        return rec(0, 0);
    }

    int rec(int idxS, int idxT) {
        if (idxT == target.length()) {
            // remove the current char from source
            return source.length() - idxS;
        }

        if (idxS == source.length()) {
            // we need to insert the current char from target 
            // at the end of source
            return target.length() - idxT ;
        }

        if (memo[idxS][idxT] != null) {
            return memo[idxS][idxT];
        }

        if (source.charAt(idxS) == target.charAt(idxT)) {
            // we can keep the current char and continue to the next
            // position
            memo[idxS][idxT] = rec(idxS+1, idxT+1);
        } else {
            int removeFromSource = rec(idxS+1, idxT),
                replaceInSource = rec(idxS+1, idxT+1),
                insertInSource = rec(idxS, idxT+1);

            memo[idxS][idxT] = 1 + min(removeFromSource, 
                                        replaceInSource,
                                        insertInSource);
        }
        return memo[idxS][idxT];
    }

    int min(int... nums) {
        int min = Integer.MAX_VALUE;
        for (int n : nums) {
            min = Math.min(min, n);
        }
        return min;
    }
}
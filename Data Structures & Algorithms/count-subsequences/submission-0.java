class Solution {
    String source;
    String target;

    Integer[][] memo;

    public int numDistinct(String s, String t) {
        if (t.length() > s.length()) {
            return 0;
        }
        this.source = s;
        this.target = t;
        this.memo = new Integer[s.length()+1][t.length()+1];
        return rec(0, 0);
    }

    int rec(int idxS, int idxT) {
        if (idxT >= target.length()) {
            return 1;
        }

        if (idxS >= source.length()) {
            return 0;
        }

        if (memo[idxS][idxT] != null) {
            return memo[idxS][idxT];
        }

        memo[idxS][idxT] = rec(idxS+1, idxT) +
            (source.charAt(idxS) == target.charAt(idxT) 
                ? rec(idxS+1, idxT+1) : 0)  ;

        return memo[idxS][idxT];
    }
}

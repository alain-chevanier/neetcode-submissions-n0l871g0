class Solution {
    String s1;
    String s2;
    String s3;
    Boolean[][] memo;

    public boolean isInterleave(String s1, String s2, String s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        this.memo = new Boolean[s1.length() + 1][s2.length() + 1];
        return rec(0, 0);
    }

    boolean rec(int idx1, int idx2) {
        int idx3 = idx1 + idx2;

        if (idx3 == s3.length()) {
            return true;
        }
        
        if (memo[idx1][idx2] != null) {
            return memo[idx1][idx2];
        }

        memo[idx1][idx2] = false;
        if (idx2 < s2.length()) {
            memo[idx1][idx2] =  memo[idx1][idx2] 
                || (s2.charAt(idx2) == s3.charAt(idx3) && rec(idx1, idx2+1));
        }

        if (idx1 < s1.length()) {
            memo[idx1][idx2] = memo[idx1][idx2] 
            || (s1.charAt(idx1) == s3.charAt(idx3) && rec(idx1+1, idx2));
        }

        return memo[idx1][idx2];
    }
}

class Solution {
    String target;
    String regex;

    Boolean[][] memo;

    public boolean isMatch(String s, String p) {
        this.target = s;
        this.regex = p;
        this.memo = new Boolean[s.length()][p.length()];
        return rec(0, 0);    
    }

    boolean rec(int idxT, int idxR) {
        boolean repeatCurRegexChar = 
            idxR < regex.length() - 1 && 
            regex.charAt(idxR+1) == '*';

        if (idxT == target.length()) {
            return idxR == regex.length() 
                || (repeatCurRegexChar && rec(idxT, idxR+2));
        }

        if (idxR == regex.length()) {
            return false;
        }

        if (memo[idxT][idxR] != null) {
            return memo[idxT][idxR];
        }
        
        char curRegexChar = regex.charAt(idxR);
        char curTargetChar = target.charAt(idxT);
        
        if (repeatCurRegexChar) {
            memo[idxT][idxR] = rec(idxT, idxR+2);
            if (curRegexChar == '.' 
                || curTargetChar == curRegexChar) {
                memo[idxT][idxR] = memo[idxT][idxR]
                                    || rec(idxT+1, idxR);
            }
        } else {
            memo[idxT][idxR] = false;
            if (curRegexChar == '.' 
                || curTargetChar == curRegexChar) {
                memo[idxT][idxR] = rec(idxT+1, idxR+1);
            }
        }
        return memo[idxT][idxR];
    }
}

class Solution {
    String str;
    Map<Integer,Integer> memo;

    public int numDecodings(String s) {
        this.str = s;
        this.memo = new HashMap<>();
        this.memo.put(s.length(), 1);
        return aux(0);
    }

    int aux(int idx) {
        if (memo.containsKey(idx)) {
            return memo.get(idx);
        }
        int count = 0;
        for (int len = 1;  len <= 2 && idx+len <= str.length(); len++) {
            // String cur = str.substring(idx, idx + len);
            int curChar = str.charAt(idx) - '0';
            if (curChar == 0 || 
                (len == 2 && curChar > 2) ||
                (len == 2 && curChar == 2 && (str.charAt(idx+1) - '0') > 6)) {
                continue;
            }
            count += aux(idx+len);
        }
        this.memo.put(idx, count);
        return count;
    }
}
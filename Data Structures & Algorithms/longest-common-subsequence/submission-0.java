class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int N = text1.length();
        int M = text2.length();

        int[][] dp = new int[N+1][M+1];

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= M; c++) {
                if (text1.charAt(r-1) == text2.charAt(c-1)) {
                    dp[r][c] = 1 + dp[r-1][c-1];
                } else {
                    dp[r][c] = Math.max(dp[r-1][c], dp[r][c-1]);
                }
            }
        }

        return dp[N][M];
    }
}

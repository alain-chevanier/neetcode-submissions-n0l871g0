class Solution {
    public int jump(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N];
        dp[0] = 0;
        // dp[i] the minimum number of steps to get to i
        for (int i = 1; i < N; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (j + nums[j] >= i) {
                    dp[i] = Math.min(dp[i], 1 + dp[j]);
                }
            }
        }
        return dp[N-1];
    }
}

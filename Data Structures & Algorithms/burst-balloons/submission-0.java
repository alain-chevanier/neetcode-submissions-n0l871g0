class Solution {
    int[][] dp;
    int[] nums;

    public int maxCoins(int[] nums) {
        int N = nums.length;
        this.dp =  new int[N][N];
        this.nums = nums;
        // for i < j, dp[i][j] represents the optimal way to burst the ballons in the range [i, ..., j]
        // dp[i][i] is nums[i-1] * nums[i] * nums[i+1]
        // given j >= i,  for each k in [i, ..., j]
        // dp[i][j] = max(nums[k-1] * nums[k] * nums[k+1] + dp[i][k] + dp[k][j])

        for (int i = 0; i < N; i++) {
            this.dp[i][i] = collectCoins(i, i-1, i+1);
        }

        for (int i = N-1; i >= 0; i--) {
            for (int j = i+1; j < N; j++) {
                dp[i][j] = Integer.MIN_VALUE;
                for (int k = i; k <= j; k++) {
                    int coinsK = collectCoins(k, i-1, j+1);
                    int coinsLeft = k > i ? dp[i][k-1] : 0;
                    int coinsRight = k < j ? dp[k+1][j] : 0;
                    dp[i][j] = Math.max(dp[i][j], coinsK + coinsLeft + coinsRight);
                }
            }
        }

        return dp[0][N-1];
    }

    int collectCoins(int i, int left, int right) {
        int leftVal = left >= 0 ? nums[left] : 1;
        int rightVal = right < this.nums.length ? nums[right] : 1;
        return nums[i] * leftVal * rightVal;
    }
}

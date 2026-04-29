class Solution {
    int[] coins;
    Integer[][] dp;

    public int change(int amount, int[] coins) {
        this.coins = coins;
        this.dp = new Integer[this.coins.length][amount+1];
        return countWays(0, amount);    
    }

    int countWays(int i, int amount) {
        if (amount == 0) {
            return 1;
        }

        if (i >= this.coins.length || amount < 0) {
            return 0;
        }

        if (this.dp[i][amount] != null) {
            return this.dp[i][amount];
        }

        this.dp[i][amount] = countWays(i, amount - this.coins[i])
            + countWays(i+1, amount);
        return this.dp[i][amount];
    }
}

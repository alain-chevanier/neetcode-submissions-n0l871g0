class Solution {
    Integer[][] dp;
    int[] price;

    public int maxProfit(int[] prices) {
        int N = prices.length;
        if (N < 2) {
            return 0;
        }

        this.dp = new Integer[N][2];
        this.price = prices;
        
        return recursion(0, 1);
    }

    int recursion(int i, int allowedToBuy) {
        if (i >= this.price.length) {
            return 0;
        }

        if (this.dp[i][allowedToBuy] != null) {
            return this.dp[i][allowedToBuy];
        }

        int cooldown = recursion(i + 1, allowedToBuy);
        if (allowedToBuy == 1) {
            int profitBuying = -this.price[i] + recursion(i+1, 0);
            this.dp[i][allowedToBuy] = Math.max(cooldown, profitBuying);
        } else {
            int profitSelling = +this.price[i] + recursion(i + 2, 1);
            this.dp[i][allowedToBuy] = Math.max(cooldown, profitSelling);
        }

        return this.dp[i][allowedToBuy];
    }
        
}

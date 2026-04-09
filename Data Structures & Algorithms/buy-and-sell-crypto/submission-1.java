class Solution {
    public int maxProfit(int[] prices) {
        int minBuyPrice = prices[0];
        int maxProfit = 0;
        for (int sellPrice : prices) {
            int profit = sellPrice - minBuyPrice;
            maxProfit = Math.max(maxProfit, profit);
            minBuyPrice = Math.min(sellPrice, minBuyPrice);
        }
        return maxProfit;
    }
}
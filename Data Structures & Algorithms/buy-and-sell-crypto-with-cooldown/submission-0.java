class Solution {
    public int maxProfit(int[] prices) {
        int N = prices.length;

        if (N < 2) {
            return 0;
        }
        // dp[i] optimal profit up to the i-th day
        //  can be defined as the max value 
        // let j < i && prices[j] < prices[i]      
        // dp[i] = max(prices[i] - prices[j] + dp[j-2])
        // dp[i] then we simply compare againts dp[i-1] which means we don't sell at i
        int[] dp = new int[N];
        dp[0] = 0;
        dp[1] = prices[0] < prices[1] ? prices[1] - prices[0] : 0;
        int max = Math.max(dp[0], dp[1]);
        Deque<int[]> deque = new ArrayDeque<>();
        deque.addLast(new int[] {prices[0], 0});
        removeAllGreaterOrEqualThan(deque, prices[1]);
        deque.addLast(new int[] {prices[1], 1});

        for (int i = 2; i < N; i++) {
            int priceI = prices[i];
            removeAllGreaterOrEqualThan(deque, priceI);

            dp[i] = dp[i-1];
            for (int[] smaller : deque) {
                int j = smaller[1];
                int priceJ = smaller[0];

                int prevBest = j >= 2 ? dp[j-2] : 0;
                
                int curProfit = priceI - priceJ + prevBest;
                dp[i] = Math.max(dp[i],  curProfit);
            }
            deque.addLast(new int[] {priceI, i});
            max = Math.max(max, dp[i]);
        }

        // System.out.println(Arrays.toString(dp));

        return max;
    }

    void addToMonotonic(int value, Deque<int[]> deque) {
        while (deque.size() > 0
            && deque.getLast()[0] >= value) {
            deque.removeLast();
        }
    }

    void removeAllGreaterOrEqualThan(Deque<int[]> deque, int value) {
        while (deque.size() > 0
            && deque.getLast()[0] >= value) {
            deque.removeLast();
        }
    }
        
}

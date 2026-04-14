class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int[] minCostUpTo = new int[len];

        minCostUpTo[0] = cost[0];
        minCostUpTo[1] = cost[1];

        for (int i = 2; i < len; i++) {
            minCostUpTo[i] = Math.min(minCostUpTo[i-1], minCostUpTo[i-2]) + cost[i];
        }

        return Math.min(minCostUpTo[len-1], minCostUpTo[len-2]);
    }
}

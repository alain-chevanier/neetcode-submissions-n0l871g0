class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int[] minToStep = new int[cost.length];
        minToStep[0] = cost[0];
        minToStep[1] = cost[1];
        for (int step = 2; step < cost.length; step++) {
            minToStep[step] = cost[step] + Math.min(minToStep[step-1], minToStep[step-2]);
        }
        return Math.min(minToStep[cost.length-1], minToStep[cost.length-2]);
    }
}

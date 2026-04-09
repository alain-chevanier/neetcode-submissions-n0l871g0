class Solution {
    public int climbStairs(int n) {
        int[] waysCount = new int[Math.max(n+1, 3 )];
        waysCount[0] = 1;
        waysCount[1] = 1;
        waysCount[2] = 2;

        for (int i = 3; i <= n; i++) {
            // comming from i-1 we have waysCount[i-1] + 1 (+1)
            // Comming from i-2 we hace waysCount[i-2] + 1 (+2)
            waysCount[i] = waysCount[i-1] + waysCount[i-2];
        }

        return waysCount[n];
    }
}

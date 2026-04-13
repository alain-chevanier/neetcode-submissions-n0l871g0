class Solution {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }

        int[] waysToGet = new int[n+1];
        waysToGet[1] = 1;
        waysToGet[2] = 2;

        for (int i = 3; i <= n; i++) {
            waysToGet[i] = waysToGet[i-1] + waysToGet[i-2]; 
        }

        return waysToGet[n];
    }

    // 3 from 1 climbing 2 1
    // 3 from 2 climbing 1 2
    // 3 we can get in 3 distinct ways
}

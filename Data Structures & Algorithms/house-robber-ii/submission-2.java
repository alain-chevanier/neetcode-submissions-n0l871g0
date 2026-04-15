class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int[] dp1 = new int[n];
        dp1[0] = nums[0];
        dp1[1] = Math.max(dp1[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp1[i] = Math.max(dp1[i-2] + nums[i], dp1[i-1]);
        }

        int[] dp2 = new int[n];
        dp2[1] = nums[1];

        for (int i = 2; i < n; i++) {
            dp2[i] = Math.max(dp2[i-2] + nums[i], dp2[i-1]);
        }

        return Math.max(dp1[n-2], dp2[n-1]);
    }

    boolean areNeighbors(int i, int j, int n) {
        int modI = mod(i, n);
        int modJ = mod(j, n);
        return Math.abs(modI - modJ) == 1;
    }

    int mod(int i, int n) {
        return i >= 0 ? i%n : n + (i%n);
    }

}

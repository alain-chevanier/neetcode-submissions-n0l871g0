class Solution {
    public int lengthOfLIS(int[] nums) {
        int N = nums.length;
        int[] lis = new int[N];
        lis[0] = 1;

        int max = 1;
        for (int i = 1; i < N; i++) {
            lis[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    lis[i] = Math.max(lis[i], 1 + lis[j]);
                }
            }
            max = Math.max(lis[i], max);
            // lis[i] = Math.max(lis[i], lis[i-1]);
        }

        return max;
    }
}

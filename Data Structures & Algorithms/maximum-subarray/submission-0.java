class Solution {
    /*
    [2,-3,4,-2,2,1,-1,4]

    */
    public int maxSubArray(int[] nums) {
        int sum = nums[0], N = nums.length;
        int max = nums[0];
        int low = 0, high = 1;
        while (high < N) {
            int cur = nums[high++];
            if (cur > (sum + cur)) {
                sum = cur;
            } else {
                sum += cur;
            }
            max = Math.max(max, sum);
        }
        return max;
    }
}

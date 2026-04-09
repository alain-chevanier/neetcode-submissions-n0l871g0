class Solution {
    public int rob(int[] nums) {
        int houses = nums.length;
        int[] maxToNth = new int[houses];
        maxToNth[0] = nums[0];

        if (houses > 1) {
            maxToNth[1] = Math.max(nums[0], nums[1]);
        }

        for (int house = 2; house < houses; house++) {
            maxToNth[house] = Math.max(maxToNth[house-2] + nums[house], maxToNth[house-1]);
        }

        return maxToNth[houses-1];
    }
}

class Solution {
    public int findMin(int[] nums) {
        // [3,4,5,6,1,2]
        // 0 -> 3, 5 -> 2
        // 3 > 2
        // min(nums) = 1

        // [2,2,1,2]
        // min(nums) = 1
        if (nums[0] >= nums[nums.length-1]) {
            return findMin(nums, 0, nums.length, nums[nums.length-1]);
        } else {
            return nums[0];
        }
    }

    int findMin(int[] nums, int ind, int length, int target) {
        if (length == 1) {
            return nums[ind];
        }
        int half = length/2;
        int center = ind + half;
        int midValue = nums[center];
        int rightLength = half - (length%2 == 0 ? 1 : 0);
        if (nums[center-1] > midValue) {
            return midValue;
        }
        if (midValue <= target) {
            return findMin(nums, ind, half, midValue);
        } else {
            return findMin(nums, center+1, rightLength, target);
        }
    }
}
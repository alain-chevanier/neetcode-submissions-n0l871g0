class Solution {
    public int[] twoSum(int[] nums, int target) {
        int beg = 0, end = nums.length-1;
        while (beg < end) {
            if (nums[beg] + nums[end] > target) {
                end--;
            } else if (nums[beg] + nums[end] < target) {
                beg++;
            } else {
                return new int[] {beg+1, end+1};
            }
        }
        return new int[0];
    }
}

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;
        while (low < high) {

            // when to increase low o high
            // if "nums[low] + nums[high] < target" how can we improve
            int sum = nums[low] + nums[high];
            if (sum > target) {
                high--;
            } else if (sum < target) {
                low++;
            } else {
                return new int[] {low+1, high+1};
            }
        }
        return new int[] {0, 1};
    }
}

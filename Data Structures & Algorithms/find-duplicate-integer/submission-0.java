class Solution {
    public int findDuplicate(int[] nums) {
        for (int n : nums) {
            int absN = n < 0 ? -n : n; 
            if (nums[absN] < 0) {
                return absN;
            }
            nums[absN] = -nums[absN];
        }
        return 0;
    }
}

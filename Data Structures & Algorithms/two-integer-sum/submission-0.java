class Solution {
    public int[] twoSum(int[] nums, int target) {
        var diffToIndex = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (diffToIndex.containsKey(diff)) {
                return new int[]{diffToIndex.get(diff), i};
            }
            diffToIndex.put(nums[i], i);
        }
        return new int[]{0, 0};
    }
}
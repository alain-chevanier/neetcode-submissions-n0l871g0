class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numAtIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            int diff = target - n;
            if (numAtIndex.containsKey(diff)) {
                return new int[] {numAtIndex.get(diff), i};
            }
            numAtIndex.put(n, i);
        }
        return new int[] {0, 1};
    }
}

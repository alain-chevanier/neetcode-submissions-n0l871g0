class Solution {
    Map<Integer, Map<Integer,Integer>> memo;
    int[] nums;

    public int findTargetSumWays(int[] nums, int target) {
        this.nums = nums;
        this.memo = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            this.memo.put(i, new HashMap<>());
        }
        return rec(0, target);
    }

    int rec(int i, int target) {
        if (i == this.nums.length) {
            return target == 0 ? 1 : 0;
        }
        if (this.memo.get(i).containsKey(target)) {
            return this.memo.get(i).get(target);
        }
        int value = rec(i+1, target - this.nums[i])
            + rec(i+1, target + this.nums[i]);
        this.memo.get(i).put(target, value);
        return this.memo.get(i).get(target);
    }
}

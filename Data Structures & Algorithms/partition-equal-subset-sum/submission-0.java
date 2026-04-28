class Solution {
    int[] nums;
    boolean[] visited;
    Map<Integer, Map<Integer, Boolean>> memo;

    public boolean canPartition(int[] nums) {
        this.nums = nums;
        this.visited = new boolean[nums.length];
        this.memo = new HashMap<>();
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }

        for (int i = 0; i < nums.length; i++) {
            this.memo.put(i, new HashMap<>());
        }

        return aux(0, 0, sum);
    }

    boolean aux(int idx, int subsetSum, int complementSum) {
        if (subsetSum == complementSum) {
            return true;
        }
        if (subsetSum > complementSum || idx == this.nums.length) {
            return false;
        }

        if (this.memo.get(idx).containsKey(subsetSum)) {
            return this.memo.get(idx).get(subsetSum);
        }

        boolean hasSolution = false;
        for (int i = idx; i < this.nums.length && !hasSolution; i++) {

            int cur = this.nums[i];
            hasSolution = aux (i + 1, subsetSum + cur, complementSum - cur);
        }
        this.memo.get(idx).put(subsetSum, hasSolution);
        return hasSolution;
    }
}

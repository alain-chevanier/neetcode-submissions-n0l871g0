class Solution {
    List<List<Integer>> answer;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        answer = new LinkedList<>();
        generate(candidates, target, 0, new LinkedList<>());
        return answer;
    }

    void generate(int[] nums, int target, int idx, List<Integer> current) {
        if (target == 0) {
            answer.add(current);
            return;
        }

        for (int i = idx; i < nums.length; i++) {
            int n = nums[i];
            if (n > target) {
                break;
            }
            if (i > idx && n == nums[i-1]) {
                continue;
            }
            List<Integer> copy = new LinkedList<>(current);
            copy.add(n);
            generate(nums, target - n, i + 1, copy);
        }
    }
}

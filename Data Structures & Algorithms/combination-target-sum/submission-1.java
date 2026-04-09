class Solution {
    List<List<Integer>> answer;

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        Arrays.sort(nums);
        answer = new LinkedList<>();
        generate(nums, target, 0, new LinkedList<>());
        return answer;    
    }

    void generate(int[] nums, int target, int ind, List<Integer> current) {
        if (target == 0) {
            answer.add(current);
            return;
        }

        for (int i = ind; i < nums.length; i++) {
            int n = nums[i];
            if (n > target) {
                break;
            }
            List<Integer> copy = new LinkedList<>(current);
            copy.add(n);
            generate(nums, target - n, i, copy);
        }
    }
}

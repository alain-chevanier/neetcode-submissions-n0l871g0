class Solution {
    List<List<Integer>> answer;

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        Arrays.sort(nums);
        answer = new LinkedList<>();

        int[] partialAnswer = new int[target];
        int idxPartialAnswer = 0;

        // generate(nums, target, 0, new LinkedList<>());
        generate(nums, target, 0, partialAnswer, 0);

        return answer;    
    }

    // void generate(int[] nums, int target, int ind, List<Integer> current) {
    void generate(int[] nums, int target, int ind, int[] pAns, int pAnsIdx) {
        if (target == 0) {
            var list = new LinkedList<Integer>();
            for (int i = 0; i < pAnsIdx; i++) {
                list.add(pAns[i]);
            }
            answer.add(list);
            //answer.add(current);
            return;
        }

        for (int i = ind; i < nums.length; i++) {
            int n = nums[i];
            if (n > target) {
                break;
            }
            // List<Integer> copy = new LinkedList<>(current);
            // copy.add(n);
            // generate(nums, target - n, i, copy);

            pAns[pAnsIdx] = n;
            generate(nums, target - n, i, pAns, pAnsIdx + 1);
        }
    }
}

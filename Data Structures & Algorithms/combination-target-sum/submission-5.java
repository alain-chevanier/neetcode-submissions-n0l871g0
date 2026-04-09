class Solution {
    List<List<Integer>> answer;
    int[] partialAnswer;

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        Arrays.sort(nums);
        answer = new LinkedList<>();

        partialAnswer = new int[target / nums[0] + 1];
        generate(nums, target, 0, 0);
        return answer;    
    }

    void generate(int[] nums, int target, int idx, int pAnsIdx) {
        if (target == 0) {
            answer.add(buildAnswer(pAnsIdx));
            return;
        }

        for (int i = idx; i < nums.length; i++) {
            int n = nums[i];
            if (n > target) {
                break;
            }
            partialAnswer[pAnsIdx] = n;
            generate(nums, target - n, i, pAnsIdx + 1);
        }
    }

    List<Integer> buildAnswer(int pAnsIdx) {
        var list = new LinkedList<Integer>();
        for (int i = 0; i < pAnsIdx; i++) {
            list.add(partialAnswer[i]);
        }
        return list;
    }
}

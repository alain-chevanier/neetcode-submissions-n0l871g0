class Solution {
    List<List<Integer>> answer;
    boolean[] used;
    int[] pAns;

    public List<List<Integer>> permute(int[] nums) {
        answer = new LinkedList<>();
        used = new boolean[100];
        pAns = new int[nums.length];

        generate(nums, 0);


        return answer;
    }

    void generate(int[] nums, int idx) {
        if (idx == nums.length) {
            answer.add(buildAnswer());
        }
        
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if (used[n + 10]) {
                continue;
            }
            pAns[idx] = n;
            used[n + 10] = true;

            generate(nums, idx + 1);

            used[n + 10] = false;
        }
    }

    List<Integer> buildAnswer() {
        List<Integer> res = new ArrayList<>();
        for (int n : pAns) {
            res.add(n);
        }
        return res;
    }
}

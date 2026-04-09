class Solution {
    List<List<Integer>> answer;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        answer = new LinkedList<>();
        answer.add(new LinkedList<>());
        generate(nums, 0, new LinkedList<>());
        return answer;
    }

    void generate(int[] nums, int idx, List<Integer> current) {
        for(int i = idx; i < nums.length; i++) {
            if (i>idx && nums[i] == nums[i-1]) {
                continue;
            }
            List<Integer> copy = new LinkedList<>(current);
            copy.add(nums[i]);
            answer.add(copy);
            generate(nums, i+1, copy);
        }
    }
}

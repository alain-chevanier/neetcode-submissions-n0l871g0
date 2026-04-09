class Solution {
    List<List<Integer>> answer;
    public List<List<Integer>> subsets(int[] nums) {
        // $$C(m, k) = \binom{m}{k} = \frac{m!}{k!(m - k)!}$$
        // where m is the length of the set
        //.      and k is the size of the subset
        // 1 * 2 * 3 * ... * (m-1) * m
        // ---------------------------
        // (1 * 2 * 3 * ... * (m - k - 1) * (m - k)) * (1 * 2 * 3 * ... * (k-1) * k)
        answer = new LinkedList<>();
        
        List<Integer> emptySet = new LinkedList<>();
        answer.add(emptySet);
        generate(nums, 0, emptySet);

        return answer;
    }

    void generate(int[] nums, int ind, List<Integer> currentList) {
        for (int i = ind; i < nums.length; i++) {
            List<Integer> copy = new LinkedList<>(currentList);
            copy.add(nums[i]);
            answer.add(copy);
            generate(nums, i+1, copy);
        }
    }


}

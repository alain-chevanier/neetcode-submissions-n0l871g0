class Solution {
    public int longestConsecutive(int[] nums) {
        var set = new HashSet<Integer>();
        for (int n : nums) {
            set.add(n);    
        }
        int max = 0;
        for (int n : nums) {
            if (!set.contains(n-1)) {
                max = Math.max(max, subsequenceLength(n, set));
            }
        }
        return max;
    }

    int subsequenceLength(int n, Set<Integer> set) {
        int length = 0;
        while (set.contains(n)) {
            length++;
            n++;
        }
        return length;
    }

}

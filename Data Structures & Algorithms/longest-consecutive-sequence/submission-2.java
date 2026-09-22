class Solution {
    public int longestConsecutive(int[] nums) {
        int maxLength = 0;
        int curLength;
        // number to longest sequence
        Set<Integer> found = new HashSet<>();

        for (int n : nums) {
            found.add(n);
        }
        for (int n : nums) {
            if (found.contains(n-1)) {
                continue;
            }
            int length = 1;
            int m = n + 1;
            while(found.contains(m)) {
                length++;
                m++;
            }
            maxLength = Math.max(maxLength, length);
        }
        return maxLength;
    }
}

class Solution {
    public int longestConsecutive(int[] nums) {
        int maxLength = 0;
        int curLength;
        // number to longest sequence
        Map<Integer, Integer> found = new HashMap<>();

        for (int n : nums) {
            found.put(n, 1);
        }
        for (int n : nums) {
            if (found.containsKey(n-1)) {
                continue;
            }
            int length = 1;
            int m = n + 1;
            while(found.containsKey(m)) {
                length++;
                m++;
            }
            maxLength = Math.max(maxLength, length);
        }
        return maxLength;
    }
}

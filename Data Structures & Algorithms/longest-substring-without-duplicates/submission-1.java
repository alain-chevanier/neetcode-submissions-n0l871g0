class Solution {
    public int lengthOfLongestSubstring(String s) {
        int leftMost = 0;
        int rightMost = 0;
        int[] occurencies = new int[256];

        int max = 0;
        while (rightMost < s.length()) {
            int currentChar = s.charAt(rightMost);
            while (occurencies[currentChar] > 0) {
                int charAtLeftMost = s.charAt(leftMost);
                occurencies[charAtLeftMost]--;
                leftMost++;
            }

            occurencies[currentChar]++;
            rightMost++;
            max = Math.max(max, rightMost - leftMost);
        }
        return max;
    }
}

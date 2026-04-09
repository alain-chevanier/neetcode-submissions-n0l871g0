class Solution {
    int[] countS1;
    int[] countS2;

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        countS1 = new int[26];
        countS2 = new int[26];
        for (char c : s1.toCharArray()) {
            int idx = c - 'a';
            countS1[idx]++;
        }

        int windowSize = s1.length();
        for (int i = 0; i < windowSize-1; i++) {
            int idx = s2.charAt(i) - 'a';
            countS2[idx]++;
        }

        for (int i = windowSize-1; i < s2.length(); i++) {
            int idx = s2.charAt(i) - 'a';
            countS2[idx]++;
            if (Arrays.equals(countS1, countS2)) {
                return true;
            }
            idx =  s2.charAt(i-windowSize + 1) - 'a';
            countS2[idx]--;
        }

        return false;
    }
}

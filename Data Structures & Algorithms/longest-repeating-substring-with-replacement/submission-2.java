class Solution {
    int[] charToFreq;

    public int characterReplacement(String s, int k) {
        // let s1 and s2 be sub strings of S such that s1 + s2 = S
        // we are looking to check if 
        charToFreq = new int[26];

        int low = 0, 
            high = 0;
        int longest = 0;
        int mostFreq = 0;
        int mostFreqChar = 0;
        while (high < s.length()) {
            int highC = s.charAt(high) - 'A';
            charToFreq[highC]++;

            if (charToFreq[highC] > mostFreq) {
                mostFreq = charToFreq[highC];
                mostFreqChar = highC;
            }

            /*while (!isStringValid(k) && low <= high) {
                int lowC = s.charAt(low) - 'A';
                charToFreq[lowC]--;
                low++;
            }*/
            while ((high - low + 1 - mostFreq) > k) {
                int lowC = s.charAt(low) - 'A';
                charToFreq[lowC]--;
                /*if (lowC == mostFreqChar) {
                    mostFreq--;
                }*/
                low++;
            }

            longest = Math.max(longest, high - low + 1);
            high++;
        }
        return longest;
    }

    boolean isStringValid(int k) {
        int mostFreq = 0;
        int mostFreqIdx = 0;
        
        for (int i = 0; i < charToFreq.length; i++) {
            if (charToFreq[i] > mostFreq) {
                mostFreq = charToFreq[i];
                mostFreqIdx = i;
            }
        }

        int totalDistinct = 0;
        for (int i = 0; i < charToFreq.length; i++) {
            if (i != mostFreqIdx) {
                totalDistinct += charToFreq[i];
            }
        }

        return totalDistinct <= k;
    }
}

class Solution {
    Map<Character, Integer> freqInT;
    Map<Character, Integer> freqInS;


    public String minWindow(String s, String t) {
        freqInS = new HashMap<>();
        freqInT = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            char curChar = t.charAt(i);
            freqInT.put(curChar, freqInT.getOrDefault(curChar, 0) + 1);
        }

        int shortest = s.length() + 1;
        int shortestLow = 0, shortestHigh = 0;
        int low = 0, high = 0;
        while (high < s.length()) {
            char charHigh = s.charAt(high);
            freqInS.put(charHigh, freqInS.getOrDefault(charHigh, 0) + 1);
            while (windowsContainsT() && low <= high) {
                int strLen = high - low + 1;
                if (strLen < shortest) {
                    shortest = strLen;
                    shortestLow = low;
                    shortestHigh = high;
                }
                char charLow = s.charAt(low);
                freqInS.put(charLow, freqInS.get(charLow) - 1);
                low++;
            }
            high++;
        }
        return shortest <= s.length() ? s.substring(shortestLow, shortestHigh+1) : "";
    }

    boolean windowsContainsT() {
        for (var entry : freqInT.entrySet()) {
            char charInT = entry.getKey();
            int freq = entry.getValue(); 
            if (freqInS.getOrDefault(charInT, 0) < freq) {
                return false;
            }
        }
        return true;
    }
}

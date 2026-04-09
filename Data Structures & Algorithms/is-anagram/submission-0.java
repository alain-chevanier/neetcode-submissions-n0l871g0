class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        var charsInS = new HashMap<Character, Integer>();
        for (char c : s.toCharArray()) {
            charsInS.put(c, charsInS.getOrDefault(c, 0) + 1);
        }

        for (char c : t.toCharArray()) {
            charsInS.put(c, charsInS.getOrDefault(c, 0) - 1);
            if (charsInS.get(c) < 0) {
                return false;
            }
        }

        return true;
    }
}

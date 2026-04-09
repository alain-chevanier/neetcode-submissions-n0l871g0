class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        var keyToStrings = new HashMap<String, List<String>>();
        for (String s : strs) {
            int[] frequencies = calcCharactersFrequency(s);
            String hashCode = Arrays.toString(frequencies);
            keyToStrings.putIfAbsent(hashCode, new LinkedList<>());
            keyToStrings.get(hashCode).add(s);
        }

        return keyToStrings.entrySet()
                            .stream()
                            .map(entry -> entry.getValue())
                            .collect(Collectors.toList());
    }

    int[] calcCharactersFrequency(String str) {
        int[] frequencies = new int[26];
        for (char c : str.toCharArray()) {
            frequencies[c - 'a']++;
        }
        return frequencies;
    }
}

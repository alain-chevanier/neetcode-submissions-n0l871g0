class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        var frequencies = new HashMap<Integer,Integer>();
        for (int n : nums) {
            frequencies.put(n, frequencies.getOrDefault(n, 0) + 1);
        }
        var frequenciesInDescOrder = 
            frequencies.entrySet()
                   .stream()
                   .sorted((a, b) -> b.getValue() - a.getValue())
                   .map(e -> e.getKey())
                   .collect(Collectors.toList());

        int[] kMostFrequent = new int[k];
        int i = 0;
        for (int value : frequenciesInDescOrder) {
            kMostFrequent[i++] = value;
            if (i == k) {
                break;
            }
        }

        return kMostFrequent;
    }
}

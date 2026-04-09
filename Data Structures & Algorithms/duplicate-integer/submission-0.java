class Solution {
    public boolean hasDuplicate(int[] nums) {
        var foundElements = new HashSet<Integer>();
        for (int n : nums) {
            if (foundElements.contains(n)) {
                return true;
            }
            foundElements.add(n);
        }
        return false;
    }
}
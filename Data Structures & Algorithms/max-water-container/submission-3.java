class Solution {
    public int maxArea(int[] hs) {
        int low = 0,
            high = hs.length-1;
        int max = 0;
        while (low < high) {
            int area = (high - low) * Math.min(hs[low], hs[high]);
            max = Math.max(max, area);
            if (hs[low] < hs[high]) {
                low++;
            } else {
                high--;
            }
        }
        return max;
    }
}

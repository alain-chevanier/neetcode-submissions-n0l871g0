class Solution {
    public int maxArea(int[] heights) {
        int n = heights.length,
            low = 0,
            high = n - 1,
            maxWater = 0;
        
        while (low < high) {
            
            int area = (high - low) * Math.min(heights[low], heights[high]);
            maxWater = Math.max(area, maxWater);
            if (heights[low] < heights[high]) {
                low++;
            } else {
                high--;
            }
        }

        return maxWater;
    }
}

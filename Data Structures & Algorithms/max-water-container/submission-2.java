class Solution {
    public int maxArea(int[] heights) {
        // given 0 <= i < j < h.length
        // base = (i - i)
        // height = min(h[i], h[j])
        // area = (j - i) * min(h[i], h[j])

        // we are trying to maximize that product
        // the largest value base is (h.length-1-0)
        // the largest value for height is min(max(h), max((h-{max(h)})))
        
        int beg = 0, end = heights.length - 1;
        int maxArea = 0;
        while (beg < end) {
            maxArea = Math.max(calcArea(heights, beg, end), maxArea);
            if (heights[beg] < heights[end]) {
                beg++;
            } else {
                end--;
            }
        }
        return maxArea;
    }

    int calcArea(int[] hs, int i, int j) {
        return (j - i) * Math.min(hs[j], hs[i]);
    }
}

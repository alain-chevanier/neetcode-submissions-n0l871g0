class Solution {
    int[] heights;

    public int largestRectangleArea(int[] heights) {
        // area(i, j) = (j-i) * min(i, i+1, ..., j)
        int n = heights.length;
        this.heights = heights;

        int low = 0,
            high = n-1;
        
        return calMaxArea(low, high);


        /*Deque<Integer> minHeight = new LinkedList<>();
        minHeight.addLast(heights[0]);
        int low = 0;
        int maxArea = 0;
        for (int i = 1; i < n; i++) {
            int curMin = minHeight.getFirst();
            int curHeight = heights[i];
            while(minHeight.size() > 0
                && curHeight < minHeight.getLast()) {
                minHeight.removeLast();
            }
            if (minHeight.isEmpty()) {
                low = i;
            }
            minHeight.addLast(curHeight);
            maxArea = Math.max(maxArea, (i - low + 1) * minHeight.getFirst());
        }
        return maxArea;*/
    }

    int calMaxArea(int low, int high) {
        if (low > high) {
            return 0;
        }
        int minHeightIdx = minInRangeIdx(low, high);

        int maxAreaOther = Math.max(calMaxArea(low, minHeightIdx-1),
                                    calMaxArea(minHeightIdx+1, high));

        return Math.max(area(low, high, minHeightIdx), maxAreaOther);
    }

    int minInRangeIdx(int low, int high) {
        int minHeight = this.heights[low];
        int idx = low;
        for (int i = low + 1; i <= high; i++) {
            if (this.heights[i] < minHeight) {
                minHeight = this.heights[i];
                idx = i;
            }
        }
        return idx;
    }

    int area(int low, int high, int minHeightIdx) {
        int base = high - low + 1;
        return base * this.heights[minHeightIdx];
    }
}

class Solution {
    public int largestRectangleArea(int[] heights) {
        // System.out.println("heights: " + Arrays.toString(heights));
        int[] nextSmaller = findNextSmallers(heights);
        // System.out.println("nextSmaller: " + Arrays.toString(nextSmaller));
        int[] prevSmaller = findPrevSmallers(heights);
        // System.out.println("prevSmaller: " + Arrays.toString(prevSmaller));

        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int area = heights[i] * (nextSmaller[i] - prevSmaller[i] - 1);
            maxArea = Math.max(area, maxArea);
        }

        return maxArea;
    }

    int[] findNextSmallers(int[] heights) {
        var nextSmaller = new int[heights.length];
        var stack = new Stack<int[]>();
        for (int i = 0; i < heights.length; i++) {
            nextSmaller[i] = heights.length;
            while (!stack.isEmpty() && stack.peek()[0] > heights[i]) {
                int[] pair = stack.pop();
                nextSmaller[pair[1]] = i;
            }
            stack.push(new int[] {heights[i], i});
        }
        return nextSmaller;
    }

    int[] findPrevSmallers(int[] heights) {
        var prevSmaller = new int[heights.length];
        var stack = new Stack<int[]>();
        for (int i = heights.length-1; i >= 0; i--) {
            prevSmaller[i] = -1;
            while (!stack.isEmpty() && stack.peek()[0] > heights[i]) {
                int[] pair = stack.pop();
                prevSmaller[pair[1]] = i;
            }
            stack.push(new int[] {heights[i], i});
        }
        return prevSmaller;
    }
}

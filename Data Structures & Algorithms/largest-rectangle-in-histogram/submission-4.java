class Solution {
    int[] heights;
    SegmentTree segmentTree;

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        this.heights = heights;
        int[] minLeft = new int[n];
        int[] minRight = new int[n];

        Stack<Integer> increasingStack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (increasingStack.size() > 0
                && heights[increasingStack.peek()] >= heights[i]) {
                increasingStack.pop();
            }
            minLeft[i] = increasingStack.isEmpty() ? -1 : increasingStack.peek();
            increasingStack.push(i);
        }

        increasingStack.clear();
        for (int i = n-1; i >= 0; i--) {
            while (increasingStack.size() > 0
                && heights[increasingStack.peek()] >= heights[i]) {
                increasingStack.pop();
            }
            minRight[i] = increasingStack.isEmpty() ? n : increasingStack.peek();
            increasingStack.push(i);
        }

        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int curMinLeft = minLeft[i],
                curMinRight = minRight[i];
            int low = curMinLeft + 1;
            int high = curMinRight - 1;
            maxArea = Math.max(maxArea, (high - low + 1) * heights[i]);
        }
        return maxArea;
    }

    public int largestRectangleArea1(int[] heights) {
        int n = heights.length;
        this.heights = heights;
        this.segmentTree = new SegmentTree(heights);
        int low = 0,
            high = n-1;
        
        return calMaxArea(low, high);
    }

    int calMaxArea(int low, int high) {
        if (low == high) {
            return this.heights[low];
        }
        int minHeightIdx = this.segmentTree.query(low, high);

        int maxAreaOther = Math.max(calMaxArea(low, minHeightIdx-1),
                                    calMaxArea(minHeightIdx+1, high));

        return Math.max(area(low, high, minHeightIdx), maxAreaOther);
    }

    int area(int low, int high, int minHeightIdx) {
        // area(i, j) = (j-i+1) * min(i, i+1, ..., j)
        int base = high - low + 1;
        return base * this.heights[minHeightIdx];
    }
}

class SegmentTree {
    int[] tree;
    int n;
    int[] nums;

    SegmentTree(int[] nums) {
        this.n = nums.length;
        this.nums = nums;
        this.tree = new int[4 * this.n];
        build(1, 0, this.n - 1);
    }

    void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = start;
            return;
        }

        int mid = (start + end) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid+1,end);
        tree[node] = nums[tree[2*node]] < nums[tree[2*node + 1]] ?  
            tree[2*node] : tree[2*node + 1]; 
    }

    int query(int L, int R) {
        return query (1, 0, this.n-1, L, R);
    }

    int query(int node, int start, int end, int L, int R) {
        // 1. Range is completely outside [L, R]
        if (R < start || end < L || end < start) {
            return -1;
        }
        // 2 Range is completely inside [L, R]
        if (L <= start && end <= R) {
            return tree[node];
        }
        // 3 Range overlaps partially
        int mid = (start + end) / 2;
        int leftMin = query(2 * node, start, mid, L, R),
            rightMin = query(2 * node + 1, mid+1, end, L, R);
        
        if (leftMin == -1) {
            return rightMin;
        }
        if (rightMin == -1) {
            return leftMin;
        }
        return nums[leftMin] < nums[rightMin] ? leftMin : rightMin;
    }
}

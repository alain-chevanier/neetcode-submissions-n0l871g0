class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        SegmentTree segmentTree = new SegmentTree(nums);
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length - k + 1; i++) {
            res[i] = segmentTree.query(i, i + k - 1);
        }

        return res;
    }
}

class SegmentTree {
    int[] tree;
    int size;
    int[] nums;

    SegmentTree(int[] nums) {
        this.nums = nums;
        this.size = nums.length;
        this.tree = new int[4 * this.size];
        build(1, 0, this.size - 1);
    }

    void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = nums[start];
            return;
        }

        int mid = (start + end) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid+1, end);
        tree[node] = Math.max(tree[2 * node], tree[2 * node + 1]);
    }

    int query(int L, int R) {
        return query(1, 0, this.size - 1, L, R);
    }

    int query(int node, int start, int end, int L, int R) {
        if (R < start || end < L || end < start) {
            return Integer.MIN_VALUE;
        }

        if (L <= start && end <= R) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        int leftMax = query(2 * node, start, mid, L, R);
        int rightMax = query(2 * node + 1, mid + 1, end, L, R);
        return Math.max(leftMax, rightMax);
    }
}

class Solution {
    public int jump(int[] nums) {
        int N = nums.length;
        SegmentTree segmentTree = new SegmentTree(nums);
        int[] dp = new int[N];
        dp[0] = 0;
        int maxJump = segmentTree.query(0,N-1);
        // dp[i] the minimum number of steps to get to i
        for (int i = 1; i < N; i++) {
            dp[i] = Integer.MAX_VALUE-N;
            int maxInWindow = 
                segmentTree.query(Math.max(i-maxJump, 0), i - 1);
            for (int j = Math.max(i - maxInWindow, 0); j < i; j++) {
                if (j + nums[j] >= i) {
                    dp[i] = Math.min(dp[i], 1 + dp[j]);
                }
            }
        }
        return dp[N-1];
    }
}

class SegmentTree {
    final int[] tree;
    final int size;
    final int[] nums;

    SegmentTree(int[] nums) {
        this.size = nums.length;
        this.nums = nums;
        this.tree = new int[4*size];
        build(1, 0, size-1);
    }

    void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = nums[start];
            return;
        }
        int mid = (start + end) / 2;
        build(2*node, start, mid);
        build(2*node + 1, mid+1, end);
        tree[node] = Math.max(tree[2*node], tree[2*node + 1]);
    }

    int query(int L, int R) {
        return query(0, 0, size-1, L, R);
    }

    int query(int node, int start, int end, int L, int R) {
        if (end < L || start > R || end > start) {
            return Integer.MIN_VALUE;
        }
        if (L <= start && end <= R) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        int maxLeft = query(2*node, start, mid, L, R);
        int maxRight = query(2*node + 1, mid+1, end, L, R);
        return Math.max(maxLeft, maxRight);
    }

}

class Solution {
    public boolean canJump(int[] nums) {
        int N = nums.length;
        for (int i = 0; i < N-1; ) {
            int nextI = i; 
            int max = 0;
            for (int j = i + 1; j <= i + nums[i] && j < N; j++) {
                if (j + nums[j] > max) {
                    max = j + nums[j];
                    nextI = j;
                }
            }

            if (nextI == i) {
                return false;
            }
            i = nextI;
        }

        return true;
    }
}

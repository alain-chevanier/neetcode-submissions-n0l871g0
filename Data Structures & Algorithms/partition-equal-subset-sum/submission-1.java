class Solution {
    int[] nums;
    Boolean[][] memo;

    public boolean canPartition(int[] nums) {
        this.nums = nums;
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }

        if (sum % 2 == 1) {
            return false;
        }

        this.memo = new Boolean[nums.length][sum/2+1];
        return aux(0, sum/2);
    }

    boolean aux(int idx, int targetSum) {
        if (targetSum == 0) {
            return true;
        }
        if (idx == this.nums.length || targetSum <  0) {
            return false;
        }

        if (this.memo[idx][targetSum] != null) {
            return this.memo[idx][targetSum];
        }

        // if (this.nums[id] <= targetSum) 

        this.memo[idx][targetSum] = 
            aux(idx + 1, targetSum) // skip it
            || aux(idx + 1, targetSum - this.nums[idx]); // take it

        return this.memo[idx][targetSum];
    }
}

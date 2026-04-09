class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length-2;) {
            if (nums[i] > 0) {
                break;
            }
            if (i>0 && nums[i] == nums[i-1]) {
                continue;
            }
            int n = -nums[i];
            int beg = i+1, end = nums.length-1;
            Integer prevSol = null;
            while (beg < end) {
                int sum = nums[beg] + nums[end];
                if (sum > n) {
                    end--;
                } else if (sum < n) {
                    beg++;
                } else  {
                    if(prevSol == null || nums[beg] != prevSol) {
                        result.add(List.of(nums[i], nums[beg], nums[end]));
                        prevSol = nums[beg];
                    }
                    beg++;
                }
            }
            int j = i + 1;
            while(j < nums.length && nums[j] == nums[i]) {
                j++;
            }
            i = j;
        }
        return result;
    }
}

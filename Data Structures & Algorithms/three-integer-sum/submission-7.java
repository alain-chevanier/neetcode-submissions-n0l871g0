class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length-2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i>0 && nums[i] == nums[i-1]) {
                continue;
            }
            int n = -nums[i];
            int beg = i+1, end = nums.length-1;
            while (beg < end) {
                int sum = nums[beg] + nums[end];
                if (sum > n) {
                    end--;
                } else if (sum < n) {
                    beg++;
                } else  {
                    result.add(List.of(nums[i], nums[beg], nums[end]));
                    beg++;
                    end--;
                    while (beg<end && nums[beg] == nums[beg-1]) {
                        beg++;
                    }
                }
            }
        }
        return result;
    }
}

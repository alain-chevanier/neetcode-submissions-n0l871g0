class Solution {
    /*
      nums:       [2,3,-2,4]
      maxProd:    [2,6,-2,4]

      nums:       [2,3,-2,-5,-4]
      maxProd:    [2,6,-2,60,20]
      minProd:    [2,3,-12,-5,-240]
     */
    public int maxProduct(int[] nums) {
        int N = nums.length;
        int[] maxProd = new int[N];
        int[] minProd = new int[N];
        maxProd[0] = nums[0];
        minProd[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < N; i++) {
            int num = nums[i];
            maxProd[i] = Math.max(num, Math.max(num * maxProd[i-1], num * minProd[i-1]));
            minProd[i] = Math.min(num, Math.min(num * maxProd[i-1], num * minProd[i-1]));
            max = Math.max(max, maxProd[i]);
        }
        return max;
    }
}
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int product = 1;
        int[] prefixProd = new int[nums.length];
        prefixProd[0] = 1;
        int[] suffixProd = new int[nums.length];
        suffixProd[nums.length-1] = 1;
        for (int i = 1, j = nums.length-2; i < nums.length; i++, j--) {
            prefixProd[i] = prefixProd[i-1] * nums[i-1]; 
            suffixProd[j] = suffixProd[j+1] * nums[j+1]; 
        }

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = prefixProd[i] * suffixProd[i];
        }

        return result;
    }
}  

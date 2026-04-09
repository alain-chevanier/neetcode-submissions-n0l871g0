class Solution {
    public int search(int[] nums, int target) {
        return searchAux(nums, 0, nums.length, target);
    }

    int searchAux(int[] nums, int ind, int length, int target) {
        if (length == 0) {
            return -1;
        }
        int half = length / 2;
        int midPosition = ind + half;
        int rightLength = half - (length%2==0 ? 1 : 0);
        int midValue = nums[midPosition];
        if (target == midValue) {
            return midPosition;
        } 
        if ((midPosition-1 >= ind && nums[midPosition-1] > midValue)
            || (midPosition+1<ind+length && midValue > nums[midPosition+1])) {
            int pos = searchAux(nums, ind, half, target);
            return pos != -1 ? pos : searchAux(nums, midPosition+1, rightLength, target);
        } else if (midValue < target) {
            return searchAux(nums, midPosition+1, rightLength, target);
        } else {
            return searchAux(nums, ind, half, target);
        }
    }
}

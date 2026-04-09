class Solution {
    public int search(int[] nums, int target) {
        return searchAux(nums, 0, nums.length, target);
    }

    int searchAux(int[] nums, int ind, int length, int target) {
        if (length == 0) {
            return -1;
        }
        int half = length/2;
        int center = ind + half;
        System.out.println(center);
        if (nums[center] == target) {
            return center;
        } else if (target > nums[center]) {
            return searchAux(nums, center+1, half-(length%2==0 ? 1 : 0), target);
        } else {
            return searchAux(nums, ind, half, target);
        }
    }
}

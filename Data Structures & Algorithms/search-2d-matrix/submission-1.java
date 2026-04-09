class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int ind = search(matrix, target);
        if (ind != -1) {
            int[][] auxMatrix = new int[matrix[ind].length][];
            for (int i = 0; i < matrix[ind].length; i++) {
                auxMatrix[i] = new int[]{matrix[ind][i], matrix[ind][i]}; 
            }
            int innerInd = search(auxMatrix, target);
            return innerInd!=-1;
        }
        return false;
    }

    public int search(int[][] nums, int target) {
        return searchAux(nums, 0, nums.length, target);
    }

    int searchAux(int[][] nums, int ind, int length, int target) {
        if (length == 0) {
            return -1;
        }
        int half = length/2;
        int center = ind + half;
        int lastInd = nums[center].length-1;
        if (nums[center][0] <= target && target <= nums[center][lastInd]) {
            return center;
        } else if (target > nums[center][lastInd]) {
            return searchAux(nums, center+1, half-(length%2==0 ? 1 : 0), target);
        } else {
            return searchAux(nums, ind, half, target);
        }
    }
}

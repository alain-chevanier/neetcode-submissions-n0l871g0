class Solution {
    public int findKthLargest(int[] nums, int k) {
        List<Integer> copy = new ArrayList<>(nums.length);
        for (int  n: nums) {
            copy.add(-n);
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>(copy);

        while (k-->1) {
            heap.poll();
        }

        return -heap.peek();
    }
}

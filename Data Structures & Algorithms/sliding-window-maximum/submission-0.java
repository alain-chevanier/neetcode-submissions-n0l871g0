class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        int resIdx = 0;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (deque.size() > 0 && deque.getLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
        }
        res[resIdx++] = deque.getFirst();

        for (int i = k; i < nums.length; i++) {
            if (nums[i-k] == deque.getFirst()) {
                deque.removeFirst();
            }
            while (deque.size() > 0 && deque.getLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
            res[resIdx++] = deque.getFirst();
        }

        return res;
    }


}

class Solution {
    public int lastStoneWeight(int[] stones) {
        var heap = new PriorityQueue<Integer>();
        for (int s : stones) {
            heap.offer(-s);
        }
        while (heap.size() > 1) {
            int a = -heap.poll();
            int b = -heap.poll();
            if (a == b) {
                continue;
            }
            if (a > b) {
                heap.offer(b-a);
            } else {
                heap.offer(a-b);
            }
        }
        if (heap.size() == 1) {
            return -heap.poll();
        } else {
            return 0;
        }
    }
}

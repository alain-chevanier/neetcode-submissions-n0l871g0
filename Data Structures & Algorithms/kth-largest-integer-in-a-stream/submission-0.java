class KthLargest {
    /*
    Explanation:
    KthLargest kthLargest = new KthLargest(3, [1, 2, 3, 3]);
    kthLargest.add(3);   // return 3, state:  [1, 2, 3, 3, 3]
    kthLargest.add(5);   // return 3, state:  [1, 2, 3, 3, 3, 5]
    kthLargest.add(6);   // return 3, state:  [1, 2, 3, 3, 3, 5, 6]
    kthLargest.add(7);   // return 5, state:  [1, 2, 3, 3, 3, 5, 6, 7]
    kthLargest.add(8);   // return 6, state:  [1, 2, 3, 3, 3, 5, 6, 8]
    */
    PriorityQueue<Integer> heap;
    int k;

    public KthLargest(int k, int[] nums) {
        this.heap = new PriorityQueue<>();
        this.k = k;
        for (int n : nums) {
            this.addValue(n);
        }
    }
    
    public int add(int val) {
        this.addValue(val);
        return heap.peek();
    }
    
    private void addValue(int n) {
        heap.offer(n);
        while (heap.size() > k) {
            heap.poll();
        }
    }
}

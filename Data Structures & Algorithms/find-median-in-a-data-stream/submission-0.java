class MedianFinder {

    PriorityQueue<Integer> smallerNumbersMaxHeap;
    PriorityQueue<Integer> biggerNumbersMinHeap;
    int size;

    public MedianFinder() {
        this.smallerNumbersMaxHeap = new PriorityQueue<>((a, b) -> b - a);
        this.biggerNumbersMinHeap = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        if (this.smallerNumbersMaxHeap.isEmpty() 
            || num <= this.smallerNumbersMaxHeap.peek()) {
            this.smallerNumbersMaxHeap.offer(num);
        } else {
            this.biggerNumbersMinHeap.offer(num);
        }

        // rebalance heaps
        while(Math.abs(this.smallerNumbersMaxHeap.size() - this.biggerNumbersMinHeap.size()) > 1) {
            if (this.smallerNumbersMaxHeap.size() > this.biggerNumbersMinHeap.size()) {
                this.biggerNumbersMinHeap.offer(this.smallerNumbersMaxHeap.poll());
            } else {
                this.smallerNumbersMaxHeap.offer(this.biggerNumbersMinHeap.poll());
            }
        }
    }
    
    public double findMedian() {
        if (this.smallerNumbersMaxHeap.size() == this.biggerNumbersMinHeap.size()) {
            return (this.smallerNumbersMaxHeap.peek() + this.biggerNumbersMinHeap.peek()) / 2.0;
        }

        return this.smallerNumbersMaxHeap.size() > this.biggerNumbersMinHeap.size() ?
            this.smallerNumbersMaxHeap.peek() : this.biggerNumbersMinHeap.peek();
    }
}

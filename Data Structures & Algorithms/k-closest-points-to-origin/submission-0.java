class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = 
            new PriorityQueue<>(this::compDist);
        for (var p : points) {
            maxHeap.add(p);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        int[][] kClosest = new int[k][];
        for (int i = k-1 ; i >= 0; i--) {
            kClosest[i] = maxHeap.poll();
        }
        return kClosest;
    }

    int compDist(int[] a, int[] b) {
        return dist(b) - dist(a);
    }

    int dist(int[] a) {
        return a[0]*a[0] + a[1]*a[1];
    }

}

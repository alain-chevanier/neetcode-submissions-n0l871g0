class Solution {
    int[] position;
    int[] speed;
    int target;

    public int carFleet(int target, int[] position, int[] speed) {
        int n = speed.length;
        this.position = position;
        this.speed = speed;
        this.target = target;

        int[][] posIdx = new int[n][2];

        for (int i = 0; i < n; i++) {
            posIdx[i] = new int[] {position[i], i};
        }

        Arrays.sort(posIdx, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return b[0] - a[0];
        });

        int fleetCount = 1;
        int first = posIdx[0][1];
        // Deque<Integer> deque = new LinkedList<>();
        for (int i = 1; i < n; i++) {
            if (!isInSameFleet(posIdx[i][1], first)) {
                fleetCount++;
                first = posIdx[i][1];
            }
            /*if (!deque.isEmpty() 
                && !isInSameFleet(posIdx[i][1], deque.getFirst())) {
                fleetCount++;
                deque.clear();
            }
            deque.addLast(posIdx[i][1]);*/
        }
        return fleetCount;
    }

    boolean isInSameFleet(int i, int first) {
        if (this.position[i] == this.position[first]) {
            return true;
        }
        if (this.speed[i] == this.speed[first]) {
            return false;
        }

        return (target - position[i]) * speed[first] <= (target - position[first]) * speed[i];

        // pos_i(t) = position[i] + t * speed[i]

        // target - position[i]     = t * speed[i]
        // t = (target - position[i]) / speed[i]

        // target - position[first] = t * speed[first]
        // t = (target - position[first]) / speed[first]
    }
}

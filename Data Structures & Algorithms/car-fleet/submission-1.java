class Solution {
    int[] position;
    int[] speed;
    int target;
    int n;

    public int carFleet(int target, int[] position, int[] speed) {
        this.n = speed.length;
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

        int fleetCount = 0;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (!deque.isEmpty() 
                && !isInSameFleet(posIdx[i][1], deque.getFirst())) {
                fleetCount++;
                deque.clear();
            }
            deque.addLast(posIdx[i][1]);
        }
        return fleetCount + 1;
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

        // position[i] + t * speed[i] = position[first] + t * speed[first]
        // t = (position[i] - position[first]) / (speed[first] - speed[i])

        // target - position[i]     = t * speed[i]
        // t = (target - position[i]) / speed[i]

        // target - position[first] = t * speed[first]
        // t = (target - position[first]) / speed[first]
    }
}

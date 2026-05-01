class Solution {
    int N;
    int[] gas;
    int[] cost;

    public int canCompleteCircuit(int[] gas, int[] cost) {
        this.N = gas.length;
        this.gas = gas;
        this.cost = cost;

        int[][] diff = new int[N][];
        for (int i = 0; i < N; i++) {
            // if gas[i] > cost[i] then we can go from i to i+1
            // by charging gas at i
            diff[i] = new int[] {gas[i] - cost[i], i};
        }

        int maxIdx = 0,
            maxDiff = diff[0][0],
            minIdx = 0,
            minDiff = diff[0][0];

        for (int i = 1; i < N; i++) {
            if (diff[i][0] > maxDiff) {
                maxDiff = diff[i][0];
                maxIdx = i;
            }

            if (diff[i][0] <= minDiff) {
                minDiff = diff[i][0];
                minIdx = i;
            }
        }

        // Arrays.sort(diff, (a,b) -> b[0] - a[0]);

        // System.out.println("maxDiff: " + Arrays.toString(diff[maxIdx]));
        // System.out.println("minDiff: " + Arrays.toString(diff[minIdx]));

        if (diff[maxIdx][0] < 0) {
            return -1;
        }

        if (aux(maxIdx)) {
            return maxIdx;
        }

        if (aux(minIdx + 1)) {
            return mod(minIdx + 1, N);
        }

        return -1;
    }

    boolean aux(int i) {
        i = mod (i, N);
        int curGas = 0;
        int size = N;
        boolean solutionFound = true;
        while (size-- > 0) {
            curGas += gas[i] - cost[i];
            if (curGas < 0) {
                solutionFound = false;
                break;
            }
            i = mod(i+1, N);
        }
        return solutionFound;
    }

    int mod(int i, int n) {
        return i >= 0 ? (i%n) : (n+(i%n))%n;
    }
}

class Solution {
    int N;
    int[] gas;
    int[] cost;

    public int canCompleteCircuit(int[] gas, int[] cost) {
        this.N = gas.length;
        this.gas = gas;
        this.cost = cost;

        int maxIdx = 0,
            maxDiff = gas[0] - cost[0],
            minIdx = 0,
            minDiff = gas[0] - cost[0];
        for (int i = 1; i < N; i++) {
            int diff = gas[i] - cost[i];
            if (diff > maxDiff) {
                maxDiff = diff;
                maxIdx = i;
            }

            if (diff <= minDiff) {
                minDiff = diff;
                minIdx = i;
            }
        }

        // Arrays.sort(diff, (a,b) -> b[0] - a[0]);

        // System.out.println("maxDiff: " + Arrays.toString(diff[maxIdx]));
        // System.out.println("minDiff: " + Arrays.toString(diff[minIdx]));

        if (maxDiff < 0) {
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
